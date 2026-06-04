package com.cardplatform.notification.adapter.in.kafka;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProposalEvent(
    UUID proposalId,
    String cpf,
    String offerType,
    String status,
    String reason,
    LocalDateTime occurredAt) {
}