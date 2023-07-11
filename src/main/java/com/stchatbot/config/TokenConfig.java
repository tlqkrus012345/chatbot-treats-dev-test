package com.stchatbot.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class TokenConfig {

    private final RedisTemplate<String, String> redisTemplate;
    public void generateToken(String userId) {
        log.info("generate token");
        String token = UUID.randomUUID().toString();
        Duration expireDuration = Duration.ofMinutes(1);
        redisTemplate.opsForValue().set(userId, token, expireDuration);

    }
    public boolean validateToken(String userId) {
        log.info("validate token");
        try {
            String token = redisTemplate.opsForValue().get(userId);
            if (token != null) {
                Long expireTime = redisTemplate.getExpire(userId);
                if (expireTime != null && expireTime > 0) {
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }
}
