package com.cardplatform.cardaccount.application.service;

import com.cardplatform.cardaccount.adapter.in.web.CardAccountRequest;
import com.cardplatform.cardaccount.adapter.in.web.CardAccountResponse;
import com.cardplatform.cardaccount.adapter.out.persistence.CardAccountEntity;
import com.cardplatform.cardaccount.adapter.out.persistence.CardAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardAccountService {
  private final CardAccountRepository repository;

  public CardAccountResponse create(CardAccountRequest request) {
    CardAccountEntity cardAccountEntity = new CardAccountEntity();
    cardAccountEntity.id = UUID.randomUUID();
    cardAccountEntity.proposalId = request.id();
    cardAccountEntity.cpf = request.cpf();
    cardAccountEntity.status = "ACTIVE";
    cardAccountEntity.createdAt = LocalDateTime.now();
    repository.save(cardAccountEntity);
    return new CardAccountResponse(cardAccountEntity.id);
  }
}