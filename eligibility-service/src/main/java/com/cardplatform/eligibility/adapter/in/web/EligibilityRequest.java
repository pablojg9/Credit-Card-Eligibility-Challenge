package com.cardplatform.eligibility.adapter.in.web;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.CustomerEligibilityData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EligibilityRequest(
    String cpf,
    BigDecimal income,
    BigDecimal investments,
    LocalDate checkingAccountCreatedAt,
    OfferType offerType,
    List<BenefitType> selectedBenefits) {
  public CustomerEligibilityData customer() {
    return new CustomerEligibilityData(income, investments, checkingAccountCreatedAt);
  }
}