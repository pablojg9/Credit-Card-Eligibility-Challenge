package com.cardplatform.benefit.adapter.in.web;

import com.cardplatform.benefit.domain.enums.BenefitType;

import java.util.List;
import java.util.UUID;

public record BenefitActivationRequest(
    UUID proposalId,
    UUID cardAccountId,
    List<BenefitType> benefits
) {
}