package com.cardplatform.proposal.adapter.out.webclient;

import com.cardplatform.proposal.domain.enums.BenefitType;

import java.util.List;
import java.util.UUID;

public record BenefitActivationRequest(
    UUID proposalId,
    UUID cardAccountId,
    List<BenefitType> benefits) {
}