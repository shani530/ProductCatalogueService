package com.example.productcatalogueservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Configuration
public class RedisConfig {
    // This class is currently empty and does not contain any methods or properties.
    // You can add your Redis-related configuration or methods here as needed.
    // For example, you might want to configure a Redis template or connection factory.
    // However, since the class is empty, it does not provide any functionality at this time.

    // implment get method using redis template
    // implment put method using redis template
    // implment delete method using redis template
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

}
