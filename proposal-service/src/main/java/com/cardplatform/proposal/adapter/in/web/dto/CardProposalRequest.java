package com.cardplatform.proposal.adapter.in.web.dto;

import com.cardplatform.proposal.domain.enums.BenefitType;
import com.cardplatform.proposal.domain.enums.OfferType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CardProposalRequest(
    @NotBlank String name,
    @NotBlank String cpf,
    @NotNull BigDecimal income,
    @NotNull BigDecimal investments,
    @NotNull LocalDate checkingAccountCreatedAt,
    @NotNull OfferType offerType,
    @NotEmpty List<BenefitType> selectedBenefits) {
}