package com.cardplatform.proposal.adapter.out.redis;

import com.cardplatform.proposal.application.port.out.IdempotencyPort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisIdempotencyAdapter implements IdempotencyPort {
  private final StringRedisTemplate redisTemplate;

  public RedisIdempotencyAdapter(
      final StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public boolean exists(final String key) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(key));
  }

  public void save(final String key, final Duration ttl) {
    redisTemplate.opsForValue().set(key, "PROCESSING", ttl);
  }
}