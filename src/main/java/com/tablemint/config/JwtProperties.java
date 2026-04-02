package com.tablemint.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app.auth")
public class JwtProperties {

    private String jwtSecret = "defaultSecretChangeInProduction";
    private long jwtExpirationMs = 86400000L;
    /** Max milliseconds without an authenticated API request before 401; 0 disables server-side idle check. */
    private long sessionIdleMs = 1_800_000L;

}
