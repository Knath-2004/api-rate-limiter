package com.ratelimiter.ratelimiter;

import com.ratelimiter.ratelimiter.model.User;
import com.ratelimiter.ratelimiter.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class RatelimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelimiterApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository repo) {
        return args -> {
            if (repo.findByApiKey("12345").isEmpty()) {
                User user = new User();
                user.setName("Test User");
                user.setApiKey("12345");
                user.setPlan("FREE");
                user.setRateLimit(5);
                repo.save(user);
            }
        };
    }
}