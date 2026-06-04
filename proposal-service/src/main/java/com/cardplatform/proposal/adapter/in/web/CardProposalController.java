package com.cardplatform.proposal.adapter.in.web;

import com.cardplatform.proposal.adapter.in.web.dto.CardProposalRequest;
import com.cardplatform.proposal.adapter.in.web.dto.CardProposalResponse;
import com.cardplatform.proposal.application.port.in.RequestCardProposalInPort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/proposals")
public class CardProposalController {

  private static final Logger log = LoggerFactory.getLogger(CardProposalController.class);

  private final RequestCardProposalInPort requestCardProposalInPort;

  public CardProposalController(
      final RequestCardProposalInPort requestCardProposalInPort) {
    this.requestCardProposalInPort = requestCardProposalInPort;
  }

  @PostMapping
  public ResponseEntity<CardProposalResponse> request(@Valid @RequestBody final CardProposalRequest request) {
    log.info("card proposal request: {}", request);
    return ResponseEntity.status(HttpStatus.CREATED).body(requestCardProposalInPort.execute(request));
  }
}