package com.player.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    /**
    For any URI within the website, Spring Boot Blocks any attempt to connect to said URI without
     correct authorization. This code ensures that only certain URI's that are passed here are allowed
     by general users. Other requests will need to be ones that are accessed though authorized accounts.
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(
                        "/api/auth/login", "/api/auth/register", "/api/health"
                ).permitAll().anyRequest().authenticated());

        return http.build();
    }
}
