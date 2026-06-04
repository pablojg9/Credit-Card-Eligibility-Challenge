package com.cardplatform.proposal.application.port.out;

import com.cardplatform.proposal.adapter.in.web.dto.CardProposalRequest;
import com.cardplatform.proposal.adapter.out.webclient.EligibilityResponse;

public interface EligibilityClientPort {
  EligibilityResponse validate(final CardProposalRequest request);
}