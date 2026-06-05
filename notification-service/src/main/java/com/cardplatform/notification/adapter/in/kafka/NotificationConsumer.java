package com.cardplatform.notification.adapter.in.kafka;

import com.cardplatform.notification.application.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

  private final NotificationService notificationService;
  private final ObjectMapper objectMapper;

  public NotificationConsumer(
      final NotificationService notificationService,
      final ObjectMapper objectMapper
  ) {
    this.notificationService = notificationService;
    this.objectMapper = objectMapper;
  }

  @KafkaListener(
      topics = {
          "card.proposal.rejected",
          "card.proposal.completed"
      },
      groupId = "notification-service"
  )
  public void consume(final String payload) {
    try {
      final ProposalEvent event =
          objectMapper.readValue(payload, ProposalEvent.class);

      notificationService.notifyCustomer(event);

    } catch (Exception ex) {
      throw new RuntimeException("Erro ao processar evento de notificação", ex);
    }
  }
}