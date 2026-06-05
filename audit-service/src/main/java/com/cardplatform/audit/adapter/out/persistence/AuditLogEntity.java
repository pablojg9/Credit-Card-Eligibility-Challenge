package com.cardplatform.audit.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogEntity {
  @Id
  public UUID id;
  public UUID proposalId;
  public String cpfMasked;
  public String status;
  public String reason;
  public LocalDateTime occurredAt;
}