package com.cardplatform.proposal.domain.model;

import com.cardplatform.proposal.adapter.in.web.dto.CardProposalRequest;
import com.cardplatform.proposal.domain.enums.BenefitType;
import com.cardplatform.proposal.domain.enums.OfferType;
import com.cardplatform.proposal.domain.enums.ProposalStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CardProposal {
  private UUID id;
  private String cpf;
  private OfferType offerType;
  private ProposalStatus status;
  private UUID cardAccountId;
  private List<BenefitType> benefits;
  private String reason;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static CardProposal create(CardProposalRequest cardProposalRequest) {
    var cardProposal = new CardProposal();
    cardProposal.id = UUID.randomUUID();
    cardProposal.cpf = cardProposalRequest.cpf();
    cardProposal.offerType = cardProposalRequest.offerType();
    cardProposal.benefits = cardProposalRequest.selectedBenefits();
    cardProposal.status = ProposalStatus.RECEIVED;
    cardProposal.createdAt = LocalDateTime.now();
    cardProposal.updatedAt = cardProposal.createdAt;
    return cardProposal;
  }

  public void reject(String reason) {
    this.status = ProposalStatus.REJECTED;
    this.reason = reason;
    this.updatedAt = LocalDateTime.now();
  }

  public void complete(UUID cardAccountId, List<BenefitType> benefits) {
    this.cardAccountId = cardAccountId;
    this.benefits = benefits;
    this.status = ProposalStatus.COMPLETED;
    this.updatedAt = LocalDateTime.now();
  }

  public UUID getId() {
    return id;
  }

  public String getCpf() {
    return cpf;
  }

  public OfferType getOfferType() {
    return offerType;
  }

  public ProposalStatus getStatus() {
    return status;
  }

  public UUID getCardAccountId() {
    return cardAccountId;
  }

  public List<BenefitType> getBenefits() {
    return benefits;
  }

  public String getReason() {
    return reason;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
