package com.cardplatform.proposal.config.usecase;

import com.cardplatform.proposal.application.port.in.RequestCardProposalInPort;
import com.cardplatform.proposal.application.port.out.BenefitClientPort;
import com.cardplatform.proposal.application.port.out.CardAccountClientPort;
import com.cardplatform.proposal.application.port.out.EligibilityClientPort;
import com.cardplatform.proposal.application.port.out.EventPublisherPort;
import com.cardplatform.proposal.application.port.out.IdempotencyPort;
import com.cardplatform.proposal.application.port.out.ProposalRepositoryPort;
import com.cardplatform.proposal.application.usecase.RequestCardProposalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestCardProposalUseCaseConfig {

  @Bean
  public RequestCardProposalInPort getRequestCardProposalInPort(
      final ProposalRepositoryPort proposalRepositoryPort,
      final EligibilityClientPort eligibilityClientPort,
      final CardAccountClientPort cardAccountClientPort,
      final BenefitClientPort benefitClientPort,
      final EventPublisherPort eventPublisherPort,
      final IdempotencyPort idempotencyPort
  ) {
    return new RequestCardProposalUseCase(
        proposalRepositoryPort,
        eligibilityClientPort,
        cardAccountClientPort,
        benefitClientPort,
        eventPublisherPort,
        idempotencyPort);
  }
}
