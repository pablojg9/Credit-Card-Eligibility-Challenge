package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.OfferType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OfferEligibilityStrategyFactory {
  private final Map<OfferType, OfferEligibilityStrategy> strategies;

  public OfferEligibilityStrategyFactory(List<OfferEligibilityStrategy> offerEligibilityStrategies) {
    this.strategies = offerEligibilityStrategies.stream()
        .collect(
            Collectors.toMap(OfferEligibilityStrategy::getOfferType, Function.identity()));
  }

  public OfferEligibilityStrategy get(OfferType offer) {
    return Optional.ofNullable(strategies.get(offer))
        .orElseThrow(() -> new IllegalArgumentException("Oferta inválida"));
  }
}