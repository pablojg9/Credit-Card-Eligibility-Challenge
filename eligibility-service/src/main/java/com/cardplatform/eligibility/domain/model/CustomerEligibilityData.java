package com.cardplatform.eligibility.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CustomerEligibilityData(
    BigDecimal income,
    BigDecimal investments,
    LocalDate checkingAccountCreatedAt) {
  public boolean hasCheckingAccountForMoreThanTwoYears() {
    return checkingAccountCreatedAt.isBefore(LocalDate.now().minusYears(2));
  }
}