package com.FoodSpringApp.FoodSpringApp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadController {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/vehiculos", "/login","/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                .requestMatchers("/alquileres").authenticated()
                .requestMatchers("/admin","/clientes").hasRole("ADMIN")
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }
}