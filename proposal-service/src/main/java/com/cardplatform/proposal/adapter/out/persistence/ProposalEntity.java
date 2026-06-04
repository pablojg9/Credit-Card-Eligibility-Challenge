package com.cardplatform.proposal.adapter.out.persistence;

import com.cardplatform.proposal.domain.enums.OfferType;
import com.cardplatform.proposal.domain.enums.ProposalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "card_proposal")
@Getter
@Setter
public class ProposalEntity {
  @Id
  public UUID id;
  public String cpf;
  @Enumerated(EnumType.STRING)
  public OfferType offerType;
  @Enumerated(EnumType.STRING)
  public ProposalStatus status;
  public UUID cardAccountId;
  public String reason;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;
}