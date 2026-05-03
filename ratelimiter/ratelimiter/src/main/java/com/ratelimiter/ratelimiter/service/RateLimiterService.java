package com.ratelimiter.ratelimiter.service;

import com.ratelimiter.ratelimiter.model.User;
import com.ratelimiter.ratelimiter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateLimiterService {

    private Map<String, Integer> tokenStore = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    public boolean allowRequest(String apiKey) {

        User user = userRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new RuntimeException("Invalid API Key"));

        tokenStore.putIfAbsent(apiKey, user.getRateLimit());

        int tokens = tokenStore.get(apiKey);

        if (tokens > 0) {
            tokenStore.put(apiKey, tokens - 1);
            return true;
        } else {
            return false;
        }
    }
}