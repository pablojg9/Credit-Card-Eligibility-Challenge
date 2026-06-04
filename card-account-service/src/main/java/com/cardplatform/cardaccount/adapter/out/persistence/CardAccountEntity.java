package com.cardplatform.cardaccount.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "card_account")
public class CardAccountEntity {
  @Id
  public UUID id;
  public UUID proposalId;
  public String cpf;
  public String status;
  public LocalDateTime createdAt;
}