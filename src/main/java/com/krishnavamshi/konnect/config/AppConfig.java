package com.krishnavamshi.konnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(
                management -> management.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            ).authorizeHttpRequests(
            // The below means, the routes starts with "/auth/" are authenticated,
            // remaining all are UnAuthenticated [other than "/auth"]...
            Authorize -> Authorize.requestMatchers("/auth/**")
                                  .authenticated()
                                  .anyRequest()
                                  .permitAll()
        ).addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
