package com.cardplatform.eligibility.domain.model;

import com.cardplatform.eligibility.domain.enums.OfferType;

import java.io.Serializable;
import java.math.BigDecimal;

public record EligibilityRule(
    OfferType offerType,
    BigDecimal minIncome,
    BigDecimal minInvestments,
    Integer minAccountYears,
    Boolean active
) implements Serializable {
}
