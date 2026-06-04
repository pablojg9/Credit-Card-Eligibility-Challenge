package com.cardplatform.proposal.application.port.out;

import com.cardplatform.proposal.domain.model.CardProposal;

public interface EventPublisherPort {
  void proposalReceived(final CardProposal cardproposal);
  void proposalRejected(final CardProposal cardproposal);
  void proposalCompleted(final CardProposal cardproposal);
}