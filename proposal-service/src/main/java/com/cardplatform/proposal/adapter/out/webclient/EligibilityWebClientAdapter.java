package com.cardplatform.proposal.adapter.out.webclient;

import com.cardplatform.proposal.adapter.in.web.dto.CardProposalRequest;
import com.cardplatform.proposal.application.port.out.EligibilityClientPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EligibilityWebClientAdapter implements EligibilityClientPort {
  private static final Logger log = LoggerFactory.getLogger(EligibilityWebClientAdapter.class);

  private final WebClient eligibilityWebClient;

  public EligibilityWebClientAdapter(
      final WebClient eligibilityWebClient) {
    this.eligibilityWebClient = eligibilityWebClient;
  }

  public EligibilityResponse validate(final CardProposalRequest request) {
    log.info("Validating eligibility for request={}", request);
    return eligibilityWebClient.post()
        .uri("/api/v1/eligibility/validate")
        .bodyValue(request)
        .retrieve()
        .bodyToMono(EligibilityResponse.class)
        .block();
  }
}