package com.cardplatform.proposal.adapter.out.kafka;

import com.cardplatform.proposal.domain.enums.OfferType;
import com.cardplatform.proposal.domain.enums.ProposalStatus;
import com.cardplatform.proposal.domain.model.CardProposal;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProposalEvent(
    UUID proposalId,
    String cpf,
    OfferType offerType,
    ProposalStatus status,
    String reason,
    LocalDateTime occurredAt) {
  public static ProposalEvent from(
      final CardProposal cardProposal) {
    return new ProposalEvent(
        cardProposal.getId(),
        cardProposal.getCpf(),
        cardProposal.getOfferType(),
        cardProposal.getStatus(),
        cardProposal.getReason(),
        LocalDateTime.now());
  }
}