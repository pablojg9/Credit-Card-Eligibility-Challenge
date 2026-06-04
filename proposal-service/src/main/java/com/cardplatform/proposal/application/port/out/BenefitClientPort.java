package com.cardplatform.proposal.application.port.out;

import com.cardplatform.proposal.adapter.out.webclient.BenefitActivationResponse;
import com.cardplatform.proposal.domain.enums.BenefitType;

import java.util.List;
import java.util.UUID;

public interface BenefitClientPort {
  BenefitActivationResponse activate(final UUID proposalId, final UUID cardAccountId, final List<BenefitType> benefits);
}