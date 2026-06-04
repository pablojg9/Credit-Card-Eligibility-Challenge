package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.CustomerEligibilityData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OfferCEligibilityStrategy implements OfferEligibilityStrategy {
  public OfferType getOfferType() {
    return OfferType.OFFER_C;
  }

  public boolean isEligible(final CustomerEligibilityData customer) {
    return customer.income().compareTo(new BigDecimal("50000")) > 0 && customer.hasCheckingAccountForMoreThanTwoYears();
  }
}