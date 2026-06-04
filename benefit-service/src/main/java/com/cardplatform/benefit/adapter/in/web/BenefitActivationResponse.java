package com.cardplatform.benefit.adapter.in.web;

import com.cardplatform.benefit.domain.enums.BenefitType;

import java.util.List;

public record BenefitActivationResponse(
    List<BenefitType> activatedBenefits
) {
}