package com.cardplatform.proposal.adapter.out.webclient;

import com.cardplatform.proposal.domain.enums.BenefitType;

import java.util.List;

public record EligibilityResponse(
    boolean eligible,
    String reason,
    List<BenefitType> eligibleBenefits
) {
}