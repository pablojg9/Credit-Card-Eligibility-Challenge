package com.cardplatform.proposal.adapter.out.webclient;

import com.cardplatform.proposal.application.port.out.CardAccountClientPort;
import com.cardplatform.proposal.domain.model.CardProposal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CardAccountWebClientAdapter implements CardAccountClientPort {
  private static final Logger log = LoggerFactory.getLogger(CardAccountWebClientAdapter.class);

  private final WebClient cardAccountWebClient;

  public CardAccountWebClientAdapter(
      final WebClient cardAccountWebClient) {
    this.cardAccountWebClient = cardAccountWebClient;
  }

  public CardAccountResponse create(CardProposal proposal) {
    log.info("Creating card account for proposalId={}", proposal.getId());
    return cardAccountWebClient
        .post()
        .uri("/api/v1/card-accounts")
        .bodyValue(proposal)
        .retrieve()
        .bodyToMono(CardAccountResponse.class)
        .block();
  }
}