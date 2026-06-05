package com.cardplatform.eligibility.domain.model;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;

import java.util.Set;

public record BenefitRule(
    BenefitType benefitType,
    Set<OfferType> allowedOffers,
    Boolean active
) {
}
