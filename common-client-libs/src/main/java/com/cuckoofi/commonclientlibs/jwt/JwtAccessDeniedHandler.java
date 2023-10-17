package com.cuckoofi.commonclientlibs.jwt;

import com.cuckoofi.commonclientlibs.constant.ResponseConstant;
import com.cuckoofi.commonclientlibs.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, java.io.IOException {

        logger.error("AccessDeniedException error. Message - {}", accessDeniedException.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(
                new Response<>(
                        HttpServletResponse.SC_FORBIDDEN,
                        false,
                        ResponseConstant.JWT_AUTH_ACCESS_FORBIDDEN)
        );
        response.setStatus( HttpServletResponse.SC_FORBIDDEN );
        response.setContentType("application/json");
        response.setContentLength(content.length());
        response.getWriter().write(content);
    }
}