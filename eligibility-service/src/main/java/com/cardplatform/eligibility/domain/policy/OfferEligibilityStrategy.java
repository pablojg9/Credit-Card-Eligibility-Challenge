package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.CustomerEligibilityData;

public interface OfferEligibilityStrategy {
  OfferType getOfferType();

  boolean isEligible(final CustomerEligibilityData customer);
}