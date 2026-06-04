package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;

import java.util.List;

public interface BenefitValidationHandler {
  void validate(final OfferType offerType, final List<BenefitType> benefits);
}