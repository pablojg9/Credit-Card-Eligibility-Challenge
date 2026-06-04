package com.cardplatform.proposal.adapter.out.webclient;

import com.cardplatform.proposal.application.port.out.BenefitClientPort;
import com.cardplatform.proposal.domain.enums.BenefitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Component
public class BenefitWebClientAdapter implements BenefitClientPort {

  private static final Logger log = LoggerFactory.getLogger(BenefitWebClientAdapter.class);

  private final WebClient benefitWebClient;

  public BenefitWebClientAdapter(
      final WebClient benefitWebClient) {
    this.benefitWebClient = benefitWebClient;
  }

  public BenefitActivationResponse activate(
      final UUID proposalId, final UUID cardAccountId, final List<BenefitType> benefits) {

    log.info("Activate Benefits for proposalId={}, cardAccountId={}, benefits={}", proposalId, cardAccountId, benefits);
    return benefitWebClient.post()
        .uri("/api/v1/benefits/activate")
        .bodyValue(
            new BenefitActivationRequest(
                proposalId,
                cardAccountId,
                benefits))
        .retrieve()
        .bodyToMono(BenefitActivationResponse.class).block();
  }
}