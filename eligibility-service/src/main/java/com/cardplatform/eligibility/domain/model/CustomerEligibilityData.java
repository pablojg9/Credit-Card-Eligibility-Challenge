package com.cardplatform.eligibility.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CustomerEligibilityData(
    UUID customerId,
    String cpf,
    String name,
    BigDecimal income,
    BigDecimal investments,
    LocalDate checkingAccountCreatedAt
) {}