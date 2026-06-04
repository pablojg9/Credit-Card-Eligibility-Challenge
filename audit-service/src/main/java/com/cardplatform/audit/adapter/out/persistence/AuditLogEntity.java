package com.cardplatform.audit.adapter.out.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_log")
public class AuditLogEntity {
  @Id
  public UUID id;
  public UUID proposalId;
  public String cpfMasked;
  public String status;
  public String reason;
  public LocalDateTime occurredAt;
}