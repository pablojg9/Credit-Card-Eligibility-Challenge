package com.cardplatform.audit.adapter.in.kafka;

import com.cardplatform.audit.adapter.out.persistence.AuditLogEntity;
import com.cardplatform.audit.adapter.out.persistence.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProposalEventConsumer {
  private final AuditLogRepository repository;

  @KafkaListener(topics = {
      "card.proposal.received",
      "card.proposal.rejected",
      "card.proposal.completed"}, groupId = "audit-service")
  public void consume(ProposalEvent event) {
    var e = new AuditLogEntity();
    e.id = UUID.randomUUID();
    e.proposalId = event.proposalId();
    e.cpfMasked = mask(event.cpf());
    e.status = event.status();
    e.reason = event.reason();
    e.occurredAt = event.occurredAt();
    repository.save(e);
  }

  private String mask(String cpf) {
    if (cpf == null || cpf.length() < 4) return "***";
    return "***.***.***-" + cpf.substring(cpf.length() - 2);
  }
}