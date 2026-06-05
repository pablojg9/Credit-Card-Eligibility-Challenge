package com.cardplatform.eligibility.adapter.out.persistence.entity;

import com.cardplatform.eligibility.domain.enums.OfferType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "eligibility_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityRuleEntity {

  @Id
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true)
  private OfferType offerType;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal minIncome;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal minInvestments;

  @Column(nullable = false)
  private Integer minAccountYears;

  @Column(nullable = false)
  private Boolean active;
}
