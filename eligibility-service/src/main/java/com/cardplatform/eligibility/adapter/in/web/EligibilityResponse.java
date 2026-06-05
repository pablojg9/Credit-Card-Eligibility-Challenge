package com.cardplatform.eligibility.adapter.in.web;

import com.cardplatform.eligibility.domain.enums.BenefitType;

import java.util.List;

public record EligibilityResponse(
    boolean eligible,
    String reason,
    List<BenefitType> eligibleBenefits,
    List<BenefitType> rejectedBenefits
) {

  public static EligibilityResponse approved(List<BenefitType> benefits) {
    return new EligibilityResponse(
        true,
        "Cliente elegível para a oferta e benefícios selecionados.",
        benefits,
        List.of()
    );
  }

  public static EligibilityResponse rejected(String reason, List<BenefitType> rejectedBenefits) {
    return new EligibilityResponse(
        false,
        reason,
        List.of(),
        rejectedBenefits
    );
  }
}