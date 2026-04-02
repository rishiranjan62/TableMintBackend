package com.tablemint.auth;

import com.tablemint.model.User;
import com.tablemint.model.UserRole;
import com.tablemint.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Set<UserRole> STAFF_ROLES = Set.of(
            UserRole.ADMIN,
            UserRole.KITCHEN_MANAGER,
            UserRole.CHEF,
            UserRole.FLOOR_MANAGER,
            UserRole.RECEPTIONIST,
            UserRole.MANAGER);

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final SessionIdleTracker sessionIdleTracker;
    private final RestaurantRestrictionService restaurantRestrictionService;

    private static final Pattern MENU_ITEM_BY_ID = Pattern.compile("^/v1/menu-items/\\d+$");
    /** e.g. /v1/tables/12 — not /v1/tables/qr/... */
    private static final Pattern TABLE_PATH_NUMERIC_ID = Pattern.compile("^/v1/tables/\\d+$");

    /** GET list, GET categories, GET by id are public; POST/PUT/DELETE require ADMIN. */
    private static boolean requiresAuth(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();
        if ("/v1/tables".equals(path) && ("GET".equalsIgnoreCase(method) || "POST".equalsIgnoreCase(method))) {
            return true;
        }
        if (TABLE_PATH_NUMERIC_ID.matcher(path).matches()
                && ("GET".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))) {
            return true;
        }
        if (path.startsWith("/v1/menu-items")) {
            if ("GET".equalsIgnoreCase(method)
                    && ("/v1/menu-items".equals(path) || path.startsWith("/v1/menu-items/categories") || MENU_ITEM_BY_ID.matcher(path).matches()))
                return false;
            return true; // POST, PUT, DELETE or other GET paths require auth
        }
        return path.startsWith("/v1/auth/me")
                || path.startsWith("/v1/auth/recent-customer-otps")
                || path.startsWith("/v1/org/")
                || path.startsWith("/v1/customer/")
                || path.startsWith("/v1/admin/")
                || path.startsWith("/v1/staff/")
                || path.startsWith("/v1/platform/")
                || (path.startsWith("/v1/tables/") && path.endsWith("/join"))
                || (path.startsWith("/v1/tables/") && path.contains("/request"))
                || path.startsWith("/v1/orders")
                || path.startsWith("/v1/order-items")
                || path.startsWith("/v1/reports")
                || path.startsWith("/v1/sales")
                || path.startsWith("/v1/audit")
                || (path.startsWith("/v1/tables/") && (path.endsWith("/bill") || path.contains("/pay-bill") || path.contains("/bill-options") || path.endsWith("/status")))
                || (path.startsWith("/v1/tables/") && path.contains("/cart"))
                || (path.startsWith("/v1/tables/") && path.contains("/table-sharing-value-added"));
    }

    private static boolean requiresStaff(String path, String method) {
        if (path.startsWith("/v1/orders")
                || path.startsWith("/v1/order-items")
                || path.startsWith("/v1/reports")
                || path.startsWith("/v1/sales")
                || path.startsWith("/v1/audit")
                || path.startsWith("/v1/staff/")
                || (path.startsWith("/v1/tables/")
                && (path.endsWith("/bill")
                || path.contains("/pay-bill")
                || path.contains("/bill-options")
                || path.contains("/table-sharing-value-added")
                || path.endsWith("/status")))) {
            return true;
        }
        // List all tables: staff only. POST/PUT/DELETE /v1/tables also staff (not customers).
        if ("/v1/tables".equals(path)) {
            return true;
        }
        if (TABLE_PATH_NUMERIC_ID.matcher(path).matches()
                && ("PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))) {
            return true;
        }
        return false;
    }

    /* POST/PUT/DELETE on /v1/menu-items and all /v1/admin/ require elevated checks in the filter. */
    private static boolean requiresAdmin(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();
        if (path.startsWith("/v1/admin/")) return true;
        if (!path.startsWith("/v1/menu-items")) return false;
        return "POST".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method);
    }

    private static boolean isMenuItemsStaffMutation(String path, String method) {
        if (!path.startsWith("/v1/menu-items") || path.startsWith("/v1/admin/")) return false;
        return "POST".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method);
    }

    private static final Pattern PLATFORM_USER_BY_ID = Pattern.compile("^/v1/platform/users/\\d+$");
    private static final Pattern PLATFORM_TENANT_ADMIN_PASSWORD_RESET =
            Pattern.compile("^/v1/platform/restaurants/\\d+/admins/reset-password$");
    private static final Pattern PLATFORM_ORGANIZATION_BY_ID = Pattern.compile("^/v1/platform/organizations/\\d+$");

    /* Mutations on /v1/platform/ (maintainers are read-only). */
    private static boolean isPlatformDataMutation(String path, String method) {
        if (!path.startsWith("/v1/platform/")) return false;
        if ("POST".equalsIgnoreCase(method) && "/v1/platform/users".equals(path)) {
            return true;
        }
        if ("POST".equalsIgnoreCase(method) && "/v1/platform/organizations".equals(path)) {
            return true;
        }
        if ("POST".equalsIgnoreCase(method) && path.matches("/v1/platform/organizations/\\d+/org-admins")) {
            return true;
        }
        if ("POST".equalsIgnoreCase(method) && PLATFORM_TENANT_ADMIN_PASSWORD_RESET.matcher(path).matches()) {
            return true;
        }
        if (PLATFORM_USER_BY_ID.matcher(path).matches()
                && ("PATCH".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))) {
            return true;
        }
        if (PLATFORM_ORGANIZATION_BY_ID.matcher(path).matches()
                && ("PATCH".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))) {
            return true;
        }
        return "PATCH".equalsIgnoreCase(method) && path.matches("/v1/platform/restaurants/\\d+");
    }

    private static void sendForbidden(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"Forbidden\",\"message\":\"" + message.replace("\"", "\\\"") + "\"}");
    }

    /** Paths {@link UserRole#ORG_ADMIN} may call (read-only org portal + account password). */
    private static boolean isOrgAdminAllowedPath(String path, String method) {
        if (path.startsWith("/v1/org/")) {
            return true;
        }
        if ("/v1/auth/me".equals(path) && "GET".equalsIgnoreCase(method)) {
            return true;
        }
        return "/v1/auth/me/password".equals(path) && "PATCH".equalsIgnoreCase(method);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (!requiresAuth(request)) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendUnauthorized(response, "Missing or invalid Authorization header.");
            return;
        }
        String token = authHeader.substring(7);
        try {
            if (!sessionIdleTracker.touchOrRejectIfIdle(token)) {
                sendUnauthorized(response, "Session ended due to inactivity. Please sign in again.");
                return;
            }
            Long userId = jwtUtil.getUserIdFromToken(token);
            UserRole userRole = jwtUtil.getRoleFromToken(token);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            request.setAttribute("userId", userId);
            request.setAttribute("userRole", userRole);
            request.setAttribute("restaurantId", user.getRestaurantId());
            request.setAttribute("organizationId", user.getOrganizationId());
            if (requiresAdmin(request)) {
                String method = request.getMethod();
                if (isMenuItemsStaffMutation(path, method)) {
                    if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin or Manager access required to change menu items.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/staff")) {
                    if ("GET".equalsIgnoreCase(method)) {
                        if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                            sendForbidden(response, "Admin or Manager access required to view staff.");
                            return;
                        }
                    } else if (userRole != UserRole.ADMIN) {
                        sendForbidden(response, "Admin access required to change staff.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/menu-items")) {
                    if ("GET".equalsIgnoreCase(method)) {
                        if (userRole != UserRole.ADMIN && userRole != UserRole.FLOOR_MANAGER
                                && userRole != UserRole.RECEPTIONIST && userRole != UserRole.MANAGER) {
                            sendForbidden(response, "Admin, Floor Manager, Receptionist, or Manager access required to view menu.");
                            return;
                        }
                    } else if (path.matches("/v1/admin/menu-items/\\d+/availability") && "PATCH".equalsIgnoreCase(method)) {
                        if (userRole != UserRole.ADMIN && userRole != UserRole.RECEPTIONIST && userRole != UserRole.MANAGER) {
                            sendForbidden(response, "Admin, Cashier, or Manager access required to update availability.");
                            return;
                        }
                    } else if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin or Manager access required for this menu action.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/aggregator-orders")) {
                    if (userRole != UserRole.ADMIN && userRole != UserRole.RECEPTIONIST && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin, Cashier, or Manager access required.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/attendance/")) {
                    if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin or Manager access required for attendance.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/leave/")) {
                    if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin or Manager access required for leave management.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/tasks")) {
                    if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin or Manager access required for task management.");
                        return;
                    }
                } else if (path.startsWith("/v1/admin/inventory")) {
                    if (userRole != UserRole.ADMIN && userRole != UserRole.MANAGER) {
                        sendForbidden(response, "Admin or Manager access required for inventory.");
                        return;
                    }
                } else if (userRole != UserRole.ADMIN) {
                    sendForbidden(response, "Admin access required.");
                    return;
                }
            }
            if (path.startsWith("/v1/platform/")) {
                if (!userRole.isPlatformInternal() || user.getRestaurantId() != null) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Forbidden\",\"message\":\"TableMint internal admin access only.\"}");
                    return;
                }
                if (isPlatformDataMutation(path, request.getMethod()) && userRole != UserRole.PLATFORM_ADMIN) {
                    sendForbidden(response, "Platform admin access required to change data or manage users.");
                    return;
                }
            } else if (path.startsWith("/v1/org/") && userRole != UserRole.ORG_ADMIN) {
                sendForbidden(response, "Organization admin access only.");
                return;
            } else if (userRole == UserRole.ORG_ADMIN && !isOrgAdminAllowedPath(path, request.getMethod())) {
                sendForbidden(response, "Chain operators can only access the organization portal and account settings.");
                return;
            } else if (requiresStaff(path, request.getMethod()) && !STAFF_ROLES.contains(userRole)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Forbidden\",\"message\":\"Staff access required.\"}");
                return;
            }
            // Recent customer OTPs: ADMIN, FLOOR_MANAGER, RECEPTIONIST, or MANAGER (share OTP with customers)
            if (path.startsWith("/v1/auth/recent-customer-otps")
                    && userRole != UserRole.ADMIN && userRole != UserRole.FLOOR_MANAGER && userRole != UserRole.RECEPTIONIST
                    && userRole != UserRole.MANAGER) {
                sendForbidden(response, "Floor Manager, Receptionist, Manager, or Admin access required.");
                return;
            }
            if (restaurantRestrictionService.isBlocked(user)) {
                sendUnauthorized(response,
                        "This restaurant has been restricted. You have been signed out. Contact TableMint support if needed.");
                return;
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.debug("Invalid JWT: {}", e.getMessage());
            sendUnauthorized(response, "Invalid or expired token.");
        }
    }

    private static void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"Unauthorized\",\"message\":\"" + message + "\"}");
    }

}
