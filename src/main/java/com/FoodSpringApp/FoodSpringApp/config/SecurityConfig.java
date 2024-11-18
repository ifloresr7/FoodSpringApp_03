package com.FoodSpringApp.FoodSpringApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // Importar BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder;  // Importar PasswordEncoder

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Crear un PasswordEncoder como un Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usamos BCryptPasswordEncoder para encriptar las contraseñas
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/vehiculos", "/login", "/registro", "/css/**", "/js/**", "/images/**", "/webjars/**","/api/**").permitAll()
                .requestMatchers("/alquileres").authenticated()
                .requestMatchers("/admin", "/clientes").hasRole("ADMIN")
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
