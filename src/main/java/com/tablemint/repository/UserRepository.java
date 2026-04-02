package com.tablemint.repository;

import com.tablemint.model.User;
import com.tablemint.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    List<User> findByRole(UserRole role);

    List<User> findByRoleInOrderByCreatedAtAsc(java.util.Collection<UserRole> roles);

    List<User> findByRoleInAndRestaurantIdOrderByCreatedAtAsc(java.util.Collection<UserRole> roles, Long restaurantId);

    List<User> findByRoleInAndRestaurantIdIsNullOrderByCreatedAtAsc(java.util.Collection<UserRole> roles);

    long countByRoleAndRestaurantIdAndEnabledTrue(UserRole role, Long restaurantId);

    long countByRoleAndRestaurantIdIsNullAndEnabledTrue(UserRole role);

    /** Active platform admins other than {@code excludeUserId} (for "last admin" checks). */
    long countByRoleAndRestaurantIdIsNullAndEnabledTrueAndIdNot(UserRole role, Long excludeUserId);

    @Query("SELECT COUNT(u) FROM User u WHERE u.restaurantId = :rid AND u.role IN :roles AND (u.enabled IS NULL OR u.enabled = true)")
    long countActiveByRestaurantIdAndRoleIn(@Param("rid") Long restaurantId, @Param("roles") Collection<UserRole> roles);

    List<User> findByRestaurantIdAndRoleAndEnabledTrue(Long restaurantId, UserRole role);

    List<User> findByOrganizationIdAndRoleOrderByCreatedAtAsc(Long organizationId, UserRole role);

    /**
     * Active tenant admins, ordered so the first row per {@code restaurantId} is the earliest-created (primary contact).
     */
    List<User> findByRestaurantIdInAndRoleAndEnabledTrueOrderByRestaurantIdAscCreatedAtAsc(
            Collection<Long> restaurantIds, UserRole role);
}
