package com.cardplatform.eligibility.adapter.in.web;

import com.cardplatform.eligibility.application.port.in.ValidateEligibilityInPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/eligibility")
public class EligibilityController {

  private static final Logger log = LoggerFactory.getLogger(EligibilityController.class);

  private final ValidateEligibilityInPort validateEligibilityInPort;

  public EligibilityController(
      final ValidateEligibilityInPort validateEligibilityInPort) {
    this.validateEligibilityInPort = validateEligibilityInPort;
  }


  @PostMapping("/validate")
  public ResponseEntity<EligibilityResponse> validate(@RequestBody final EligibilityRequest request) {
    log.info("Validating eligibility for request: {}", request);
    return ResponseEntity.status(HttpStatus.OK).body(validateEligibilityInPort.validateEligibility(request));
  }
}