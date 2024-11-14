package com.FoodSpringApp.FoodSpringApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
           
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login")            
                    .permitAll()
            )
            .logout(logout -> 
                logout
                    .permitAll()
            );

        return http.build();
    }
}