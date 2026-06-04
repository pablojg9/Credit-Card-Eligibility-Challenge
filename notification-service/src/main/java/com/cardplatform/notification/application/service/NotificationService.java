package com.cardplatform.notification.application.service;

import com.cardplatform.notification.adapter.in.kafka.ProposalEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

  public void notifyCustomer(ProposalEvent event) {
    log.info("Notificando cliente. proposalId={}, status={}, reason={}", event.proposalId(), event.status(), event.reason());
  }
}