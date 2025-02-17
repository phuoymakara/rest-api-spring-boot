package com.springapp.spring_api.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.gson.Gson;
import com.springapp.spring_api.response.ApiGlobalErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;

@Slf4j
public class UserForbiddenErrorHandler implements AccessDeniedHandler{
    
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {

        log.info("Insufficient privileges to perform this action.");

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(createErrorBody());

    }

    private String createErrorBody() {
        final ApiGlobalErrorResponse apiGlobalErrorResponse = ApiGlobalErrorResponse.unAuthorized("");
        return new Gson().toJson(apiGlobalErrorResponse);
    }
}
