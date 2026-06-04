package com.cardplatform.cardaccount.adapter.in.web;

import com.cardplatform.cardaccount.application.service.CardAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/card-accounts")
public class CardAccountController {

  private static final Logger log = LoggerFactory.getLogger(CardAccountController.class);

  private final CardAccountService cardAccountService;

  public CardAccountController(final CardAccountService cardAccountService) {
    this.cardAccountService = cardAccountService;
  }

  @PostMapping
  public ResponseEntity<CardAccountResponse> create(@RequestBody CardAccountRequest request) {
    log.info("Create card account request: {}", request);
    return ResponseEntity.status(HttpStatus.CREATED).body(cardAccountService.create(request));
  }
}