package com.cardplatform.proposal.application.port.in;

import com.cardplatform.proposal.adapter.in.web.dto.CardProposalRequest;
import com.cardplatform.proposal.adapter.in.web.dto.CardProposalResponse;

public interface RequestCardProposalInPort {
  CardProposalResponse execute(final CardProposalRequest request);
}