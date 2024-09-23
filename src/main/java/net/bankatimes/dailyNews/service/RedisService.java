package net.bankatimes.dailyNews.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String jsonValue = redisTemplate.opsForValue().get(key);
            if (jsonValue == null) {
                log.warn("No value found for key: {}", key);
                return null;
            }
            return objectMapper.readValue(jsonValue, entityClass);
        } catch (Exception e) {
            log.error("Error retrieving value from Redis for key: {}", key, e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {
        try {
            String jsonValue = objectMapper.writeValueAsString(o);
            if (ttl != null) {
                redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, jsonValue);
            }
        } catch (JsonProcessingException e) {
            log.error("Error serializing object to JSON for key: {}", key, e);
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }
}