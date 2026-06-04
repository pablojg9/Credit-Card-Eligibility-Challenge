package com.cardplatform.benefit.adapter.out.persistence;

import com.cardplatform.benefit.domain.enums.BenefitType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activated_benefit")
public class BenefitEntity {
  @Id
  public UUID id;
  public UUID proposalId;
  public UUID cardAccountId;
  @Enumerated(EnumType.STRING)
  public BenefitType benefitType;
  public String status;
  public LocalDateTime activatedAt;
}