package com.cardplatform.notification.adapter.in.kafka;

import com.cardplatform.notification.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
  private final NotificationService notificationService;

  public NotificationConsumer(
      final NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @KafkaListener(topics = {
      "card.proposal.rejected",
      "card.proposal.completed"},
      groupId = "notification-service")
  public void consume(final ProposalEvent event) {
    notificationService.notifyCustomer(event);
  }
}