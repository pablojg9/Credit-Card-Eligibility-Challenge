package com.cardplatform.benefit.adapter.in.web;

import com.cardplatform.benefit.application.service.BenefitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/benefits")
public class BenefitController {
  private static final Logger log = LoggerFactory.getLogger(BenefitController.class);

  private final BenefitService benefitService;

  public BenefitController(final BenefitService benefitService) {
    this.benefitService = benefitService;
  }

  @PostMapping("/activate")
  public ResponseEntity<BenefitActivationResponse> activate(@RequestBody final BenefitActivationRequest request) {
    log.info("Activating benefits for request: {}", request);
    return ResponseEntity.status(HttpStatus.OK).body(benefitService.activate(request));
  }
}