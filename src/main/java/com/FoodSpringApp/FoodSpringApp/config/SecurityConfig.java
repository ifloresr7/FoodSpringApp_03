package com.FoodSpringApp.FoodSpringApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       

        http
         .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**") // Desactiva CSRF solo para las rutas que comienzan con "/api/"
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Personaliza el repositorio de tokens CSRF usando cookies
            )
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/", "/vehiculos", "/login","/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
            .requestMatchers("/alquileres").authenticated()
            .requestMatchers("/admin","/clientes").hasRole("ADMIN")
        )
        .formLogin((form) -> form
            .loginPage("/login")
            .permitAll()
            .failureUrl("/login?error=true")
        )
        .logout((logout) -> logout.permitAll());
    return http.build();

    }
}