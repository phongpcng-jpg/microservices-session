package io.github.nguyenquephong13062003.customer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for Customer Service.
 *
 * <p>Authentication is not implemented yet because it is outside
 * the scope of this exercise. Password hashing is still provided
 * through BCrypt.</p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the HTTP security filter chain.
     *
     * @param http HTTP security configuration
     * @return configured security filter chain
     * @throws Exception when security configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
    
}
