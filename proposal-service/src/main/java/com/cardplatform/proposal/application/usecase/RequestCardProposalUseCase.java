package com.cardplatform.proposal.application.usecase;

import com.cardplatform.proposal.adapter.in.web.dto.CardProposalRequest;
import com.cardplatform.proposal.adapter.in.web.dto.CardProposalResponse;
import com.cardplatform.proposal.adapter.out.webclient.BenefitActivationResponse;
import com.cardplatform.proposal.adapter.out.webclient.CardAccountResponse;
import com.cardplatform.proposal.adapter.out.webclient.EligibilityResponse;
import com.cardplatform.proposal.application.port.in.RequestCardProposalInPort;
import com.cardplatform.proposal.application.port.out.BenefitClientPort;
import com.cardplatform.proposal.application.port.out.CardAccountClientPort;
import com.cardplatform.proposal.application.port.out.EligibilityClientPort;
import com.cardplatform.proposal.application.port.out.EventPublisherPort;
import com.cardplatform.proposal.application.port.out.IdempotencyPort;
import com.cardplatform.proposal.application.port.out.ProposalRepositoryPort;
import com.cardplatform.proposal.domain.exception.DuplicateProposalException;
import com.cardplatform.proposal.domain.model.CardProposal;
import com.cardplatform.proposal.domain.utils.mask.MaskUtils;

import java.time.Duration;

public class RequestCardProposalUseCase implements RequestCardProposalInPort {
  private final ProposalRepositoryPort proposalRepository;
  private final EligibilityClientPort eligibilityClient;
  private final CardAccountClientPort cardAccountClient;
  private final BenefitClientPort benefitClient;
  private final EventPublisherPort eventPublisher;
  private final IdempotencyPort idempotencyPort;

  public RequestCardProposalUseCase(
      final ProposalRepositoryPort proposalRepository,
      final EligibilityClientPort eligibilityClient,
      final CardAccountClientPort cardAccountClient,
      final BenefitClientPort benefitClient,
      final EventPublisherPort eventPublisher,
      final IdempotencyPort idempotencyPort) {
    this.proposalRepository = proposalRepository;
    this.eligibilityClient = eligibilityClient;
    this.cardAccountClient = cardAccountClient;
    this.benefitClient = benefitClient;
    this.eventPublisher = eventPublisher;
    this.idempotencyPort = idempotencyPort;
  }

  public CardProposalResponse execute(final CardProposalRequest request) {
    final String key = "proposal:idempotency:" + request.cpf() + ":" + request.offerType();
    if (idempotencyPort.exists(key)) {
      throw new DuplicateProposalException(
          "A proposal for CPF " + MaskUtils.maskCpf(request.cpf()) + " and offer type " + request.offerType() + " is already being processed."
      );
    }

    idempotencyPort.save(key, Duration.ofMinutes(10));

    CardProposal cardProposal = CardProposal.create(request);
    proposalRepository.save(cardProposal);
    eventPublisher.proposalReceived(cardProposal);

    final EligibilityResponse eligibility = eligibilityClient.validate(request);


    if (!eligibility.eligible()) {
      cardProposal.reject(eligibility.reason());
      proposalRepository.save(cardProposal);
      eventPublisher.proposalRejected(cardProposal);
      return CardProposalResponse.rejected(cardProposal);
    }
    final CardAccountResponse account = cardAccountClient.create(cardProposal);
    final BenefitActivationResponse benefits = benefitClient.activate(cardProposal.getId(),
        account.cardAccountId(),
        eligibility.eligibleBenefits());

    cardProposal.complete(account.cardAccountId(), benefits.activatedBenefits());
    proposalRepository.save(cardProposal);
    eventPublisher.proposalCompleted(cardProposal);
    return CardProposalResponse.approved(cardProposal);
  }
}
