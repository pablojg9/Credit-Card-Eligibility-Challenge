package com.cardplatform.proposal.adapter.in.web.dto;

import com.cardplatform.proposal.domain.enums.BenefitType;
import com.cardplatform.proposal.domain.enums.ProposalStatus;
import com.cardplatform.proposal.domain.model.CardProposal;

import java.util.List;
import java.util.UUID;

public record CardProposalResponse(
    UUID proposalId,
    ProposalStatus status,
    boolean cardCreated,
    UUID cardAccountId,
    List<BenefitType> activatedBenefits,
    String message) {
  public static CardProposalResponse rejected(final CardProposal cardProposal) {
    return new CardProposalResponse(
        cardProposal.getId(),
        cardProposal.getStatus(),
        false,
        null,
        List.of(),
        cardProposal.getReason());
  }

  public static CardProposalResponse approved(final CardProposal cardProposal) {
    return new CardProposalResponse(
        cardProposal.getId(),
        cardProposal.getStatus(),
        true,
        cardProposal.getCardAccountId(),
        cardProposal.getBenefits(),
        "Cartão criado e benefícios ativados.");
  }
}
