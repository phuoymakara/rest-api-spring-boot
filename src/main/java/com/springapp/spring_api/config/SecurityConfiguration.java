package com.springapp.spring_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  
public class SecurityConfiguration  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())// Disable CSRF for APIs
            .httpBasic(basic -> basic.disable());

                //.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                // .authorizeHttpRequests(auth -> auth
                //                 //.requestMatchers("/users/**").permitAll() // Allow public access to specific endpoints
                //                 .anyRequest().authenticated() // Secure all other endpoints
                // )
     

        return http.build();
    }
}
