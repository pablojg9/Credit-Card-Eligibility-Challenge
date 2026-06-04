package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.CustomerEligibilityData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OfferBEligibilityStrategy implements OfferEligibilityStrategy {
  public OfferType getOfferType() {
    return OfferType.OFFER_B;
  }

  public boolean isEligible(final CustomerEligibilityData customer) {
    return customer.income().compareTo(new BigDecimal("15000")) > 0
        && customer.investments().compareTo(new BigDecimal("5000")) > 0;
  }
}