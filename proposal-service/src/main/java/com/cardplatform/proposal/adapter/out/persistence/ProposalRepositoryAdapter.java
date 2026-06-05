package com.cardplatform.proposal.adapter.out.persistence;

import com.cardplatform.proposal.application.port.out.ProposalRepositoryPort;
import com.cardplatform.proposal.domain.model.CardProposal;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProposalRepositoryAdapter implements ProposalRepositoryPort {
  private final ProposalJpaRepository repository;

  public ProposalRepositoryAdapter(
      final ProposalJpaRepository repository) {
    this.repository = repository;
  }

  public void save(final CardProposal cardproposal) {
    var proposalEntity = new ProposalEntity();
    proposalEntity.setId(cardproposal.getId());
    proposalEntity.setCpf(cardproposal.getCpf());
    proposalEntity.setOfferType(cardproposal.getOfferType());
    proposalEntity.setStatus(cardproposal.getStatus());
    proposalEntity.setCardAccountId(cardproposal.getCardAccountId());
    proposalEntity.setReason(cardproposal.getReason());;
    proposalEntity.setCreatedAt(cardproposal.getCreatedAt());
    proposalEntity.setUpdatedAt(cardproposal.getUpdatedAt());
    repository.save(proposalEntity);
  }
}