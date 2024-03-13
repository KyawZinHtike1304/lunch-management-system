package com.example.lunchmanagement.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class SecurityUtils {
    public static boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(authority -> authority.getAuthority().equals(role));
    }
}