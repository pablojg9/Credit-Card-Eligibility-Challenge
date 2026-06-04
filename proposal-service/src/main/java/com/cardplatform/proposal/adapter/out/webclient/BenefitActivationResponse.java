package com.cardplatform.proposal.adapter.out.webclient;

import com.cardplatform.proposal.domain.enums.BenefitType;

import java.util.List;

public record BenefitActivationResponse(
    List<BenefitType> activatedBenefits) {
}