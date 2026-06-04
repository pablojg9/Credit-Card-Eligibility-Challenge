package com.cardplatform.proposal.application.port.out;

import com.cardplatform.proposal.domain.model.CardProposal;

public interface ProposalRepositoryPort {
  void save(final CardProposal proposal);
}