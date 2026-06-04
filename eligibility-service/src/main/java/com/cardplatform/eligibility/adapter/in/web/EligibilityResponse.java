package com.cardplatform.eligibility.adapter.in.web;

import com.cardplatform.eligibility.domain.enums.BenefitType;

import java.util.List;

public record EligibilityResponse(
    boolean eligible,
    String reason,
    List<BenefitType> eligibleBenefits
) {
  public static EligibilityResponse approved(final List<BenefitType> benefitType) {
    return new EligibilityResponse(
        true,
        null,
        benefitType);
  }

  public static EligibilityResponse rejected(final String message) {
    return new EligibilityResponse(
        false,
        message,
        List.of());
  }
}