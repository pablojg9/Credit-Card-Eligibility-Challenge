package com.cardplatform.eligibility.adapter.in.web;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record EligibilityRequest(
    UUID customerId,
    String cpf,
    String name,
    BigDecimal income,
    BigDecimal investments,
    LocalDate checkingAccountCreatedAt,
    OfferType offerType,
    List<BenefitType> selectedBenefits) {
}