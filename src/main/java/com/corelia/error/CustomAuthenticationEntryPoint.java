package com.corelia.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String message = (authException != null && authException.getMessage() != null) ? authException.getMessage() : "Unauthorized";
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        // Construct the JSON response
        String jsonResponse =
                String.format("{\"success\": \"%s\", \"status\": %d, \"message\": \"%s\",\"details\": %s,\"timestamp\": \"%s\"}",
                        Boolean.FALSE,
                        HttpStatus.UNAUTHORIZED.value(),
                        message,
                        new ObjectMapper().writeValueAsString(List.of(message))
                        ,LocalDateTime.now());

        response.getWriter().write(jsonResponse);
    }
}
