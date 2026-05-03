package com.ratelimiter.ratelimiter.config;

import com.ratelimiter.ratelimiter.service.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    @Autowired
    private RateLimiterService rateLimiterService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // ⭐ Allow static files (dashboard.html, js, css) without API key
        String path = request.getRequestURI();
        if (path.startsWith("/dashboard") || path.startsWith("/static") || path.contains(".html")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ⭐ Get API key
        String apiKey = request.getHeader("api_key");
        System.out.println("Received API Key: " + apiKey);

        // ⭐ Missing API key
        if (apiKey == null || apiKey.isEmpty()) {
            response.setStatus(400);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Missing API Key");
            return;
        }

        // ⭐ Rate limit check
        if (!rateLimiterService.allowRequest(apiKey)) {
            response.setStatus(429);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Rate limit exceeded");
            return;
        }

        // ⭐ Continue request
        filterChain.doFilter(request, response);
    }
}