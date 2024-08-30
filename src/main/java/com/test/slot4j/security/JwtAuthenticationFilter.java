package com.test.slot4j.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Optional<String> tokenOpt = Optional.ofNullable(request.getHeader("Authorization"))
                .filter(header -> header.startsWith("Bearer "))
                .map(header -> header.substring(7)); // token after "Bearer "

        if (tokenOpt.isPresent()) {
            String token = tokenOpt.get();
            if (jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
