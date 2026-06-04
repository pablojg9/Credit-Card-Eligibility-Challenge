package com.cardplatform.proposal.adapter.out.kafka;

import com.cardplatform.proposal.application.port.out.EventPublisherPort;
import com.cardplatform.proposal.domain.model.CardProposal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisherAdapter implements EventPublisherPort {
  private static final Logger log = LoggerFactory.getLogger(KafkaEventPublisherAdapter.class);

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void proposalReceived(final CardProposal cardProposal) {
    final var future = kafkaTemplate.send(
        "card.proposal.received",
        cardProposal.getId().toString(),
        ProposalEvent.from(cardProposal));
    log.info("Sending proposal.received event for proposalId={}", cardProposal.getId());
  }

  public void proposalRejected(final CardProposal cardProposal) {
    final var future = kafkaTemplate.send(
        "card.proposal.rejected",
        cardProposal.getId().toString(),
        ProposalEvent.from(cardProposal));
    log.info("Sending proposal.rejected event for proposalId={}", cardProposal.getId());
  }

  public void proposalCompleted(final CardProposal cardProposal) {
    final var future = kafkaTemplate.send(
        "card.proposal.completed",
        cardProposal.getId().toString(),
        ProposalEvent.from(cardProposal));
    log.info("Sending proposal.completed event for proposalId={}", cardProposal.getId());
  }
}