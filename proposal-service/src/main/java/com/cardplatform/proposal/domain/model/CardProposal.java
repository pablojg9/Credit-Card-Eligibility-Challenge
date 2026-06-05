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
    CardProposal cardProposal = new CardProposal();
    cardProposal.setId(UUID.randomUUID());
    cardProposal.setCpf(cardProposalRequest.cpf());
    cardProposal.setOfferType(cardProposalRequest.offerType());
    cardProposal.setBenefits(cardProposalRequest.selectedBenefits());
    cardProposal.setStatus(ProposalStatus.RECEIVED);
    cardProposal.setCreatedAt(LocalDateTime.now());
    cardProposal.setUpdatedAt(cardProposal.getCreatedAt());
    return cardProposal;
  }

  public void reject(String reason) {
    this.status = ProposalStatus.REJECTED;
    this.reason = reason;
    this.setUpdatedAt(LocalDateTime.now());
  }

  public void complete(UUID cardAccountId, List<BenefitType> benefits) {
    this.cardAccountId = cardAccountId;
    this.benefits = benefits;
    this.status = ProposalStatus.COMPLETED;
    this.setUpdatedAt(LocalDateTime.now());
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

  public void setId(UUID id) {
    this.id = id;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setOfferType(OfferType offerType) {
    this.offerType = offerType;
  }

  public void setStatus(ProposalStatus status) {
    this.status = status;
  }

  public void setCardAccountId(UUID cardAccountId) {
    this.cardAccountId = cardAccountId;
  }

  public void setBenefits(List<BenefitType> benefits) {
    this.benefits = benefits;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
