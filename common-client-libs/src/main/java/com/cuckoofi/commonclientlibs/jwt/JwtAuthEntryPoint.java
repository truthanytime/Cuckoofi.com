package com.cuckoofi.commonclientlibs.jwt;

import com.cuckoofi.commonclientlibs.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        logger.error("Unauthorized error. Message - {}", authException.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(
                new Response<>(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        false,
                        authException.getMessage())
        );
        response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
        response.setContentType("application/json");
        response.setContentLength(content.length());
        response.getWriter().write(content);
    }
}
