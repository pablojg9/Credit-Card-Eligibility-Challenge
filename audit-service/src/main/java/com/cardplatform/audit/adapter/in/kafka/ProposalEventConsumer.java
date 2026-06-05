package com.cardplatform.audit.adapter.in.kafka;

import com.cardplatform.audit.adapter.out.persistence.AuditLogEntity;
import com.cardplatform.audit.adapter.out.persistence.AuditLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class ProposalEventConsumer {

  private final ObjectMapper objectMapper;
  private final AuditLogRepository auditLogRepository;

  public ProposalEventConsumer(
      final ObjectMapper objectMapper, final AuditLogRepository auditLogRepository) {
    this.objectMapper = objectMapper;
    this.auditLogRepository = auditLogRepository;
  }

  @KafkaListener(
      topics = {
          "card.proposal.rejected",
          "card.proposal.completed"
      },
      groupId = "audit-service"
  )
  public void consume(
      String payload,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic
  ) {
    try {
      log.info("Audit recebeu evento. topic={}, payload={}", topic, payload);

      ProposalEvent event = objectMapper.readValue(payload, ProposalEvent.class);

      AuditLogEntity entity = AuditLogEntity.builder()
          .id(UUID.randomUUID())
          .proposalId(event.proposalId())
          .cpfMasked(maskCpf(event.cpf()))
          .status(event.status())
          .reason(event.reason())
          .occurredAt(LocalDateTime.now())
          .build();

      auditLogRepository.save(entity);

      log.info("Audit salvo com sucesso. proposalId={}", event.proposalId());

    } catch (Exception ex) {
      log.error("Erro ao processar evento no audit-service. payload={}", payload, ex);
    }
  }

  private String maskCpf(String cpf) {
    if (cpf == null || cpf.length() < 4) {
      return "***";
    }

    return "***.***.***-" + cpf.substring(cpf.length() - 2);
  }
}