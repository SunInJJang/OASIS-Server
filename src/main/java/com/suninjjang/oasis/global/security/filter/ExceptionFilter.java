package com.suninjjang.oasis.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suninjjang.oasis.global.error.ErrorCode;
import com.suninjjang.oasis.global.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try{
            filterChain.doFilter(request,response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void responseError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(),errorCode.getMessage());
        String valueAsString = objectMapper.writeValueAsString(errorResponse);
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write(valueAsString);
    }
}