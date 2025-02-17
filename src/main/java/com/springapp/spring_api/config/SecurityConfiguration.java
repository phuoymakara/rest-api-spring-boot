package com.springapp.spring_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  
public class SecurityConfiguration  {

    private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling((exception)->
                    exception
                            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                            .accessDeniedHandler(new UserForbiddenErrorHandler()));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(csrf -> csrf.disable())// Disable CSRF for APIs
            .addFilterBefore(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(basic -> basic.disable());
        

        

                //.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                // .authorizeHttpRequests(auth -> auth
                //                 //.requestMatchers("/users/**").permitAll() // Allow public access to specific endpoints
                //                 .anyRequest().authenticated() // Secure all other endpoints
                // )
     

        return http.build();
    }
}
