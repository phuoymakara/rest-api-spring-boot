package com.springapp.spring_api.config;

import java.io.IOException;
import com.google.gson.Gson;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.springapp.spring_api.response.ApiGlobalErrorResponse;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
    
  @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(createErrorBody());
    }

    private String createErrorBody() {
        final ApiGlobalErrorResponse apiGlobalErrorResponse = ApiGlobalErrorResponse.unAuthenticated();
        return new Gson().toJson(apiGlobalErrorResponse);
    }
}
