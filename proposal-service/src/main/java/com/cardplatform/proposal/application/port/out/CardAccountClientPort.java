package com.cardplatform.proposal.application.port.out;

import com.cardplatform.proposal.adapter.out.webclient.CardAccountResponse;
import com.cardplatform.proposal.domain.model.CardProposal;

public interface CardAccountClientPort {
  CardAccountResponse create(final CardProposal proposal);
}