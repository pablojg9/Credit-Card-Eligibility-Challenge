package com.cardplatform.eligibility.adapter.out.redis;

import com.cardplatform.eligibility.application.port.out.EligibilityRuleCacheOutPort;
import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.BenefitRule;
import com.cardplatform.eligibility.domain.model.EligibilityRule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Optional;

@Component
public class EligibilityRuleCacheAdapter implements EligibilityRuleCacheOutPort {
  private final RedisTemplate<String, String> redisTemplate;
  private final ObjectMapper objectMapper;

  public EligibilityRuleCacheAdapter(
      final RedisTemplate<String, String> redisTemplate,
      final ObjectMapper objectMapper) {
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  @Override
  public Optional<EligibilityRule> findOfferRule(final OfferType offerType) {
    final String json = redisTemplate.opsForValue().get("eligibility:offer:" + offerType.name());

    if (json == null) {
      return Optional.empty();
    }

    try {
      return Optional.of(objectMapper.readValue(json, EligibilityRule.class));
    } catch (Exception ex) {
      return Optional.empty();
    }
  }

  @Override
  public void saveOfferRule(final EligibilityRule eligibilityRule, Duration ttlDuration) {
    try {
      String json = objectMapper.writeValueAsString(eligibilityRule);

      redisTemplate.opsForValue().set(
          "eligibility:offer:" + eligibilityRule.offerType().name(),
          json,
          ttlDuration
      );
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao salvar regra de oferta no Redis", ex);
    }
  }

  @Override
  public Optional<BenefitRule> findBenefitRule(final BenefitType benefitType) {
    final String json = redisTemplate.opsForValue()
        .get("eligibility:benefit:" + benefitType.name());

    if (json == null) {
      return Optional.empty();
    }

    try {
      return Optional.of(objectMapper.readValue(json, BenefitRule.class));
    } catch (Exception ex) {
      return Optional.empty();
    }
  }

  @Override
  public void saveBenefitRule(final BenefitRule rule, final Duration ttlDuration) {
    try {
      final String json = objectMapper.writeValueAsString(rule);

      redisTemplate.opsForValue().set(
          "eligibility:benefit:" + rule.benefitType().name(),
          json,
          ttlDuration
      );
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao salvar regra de benefício no Redis", ex);
    }
  }
}
