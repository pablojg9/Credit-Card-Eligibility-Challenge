package com.cardplatform.eligibility.adapter.out.persistence.entity;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "benefit_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitRuleEntity {

  @Id
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true)
  private BenefitType benefitType;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "benefit_rule_allowed_offer", joinColumns = @JoinColumn(name = "benefit_rule_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "offer_type", nullable = false)
  private Set<OfferType> allowedOffers = new HashSet<>();

  @Column(nullable = false)
  private Boolean active;
}