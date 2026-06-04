package com.cardplatform.eligibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EligibilityServiceApplication {
  private static final Logger log = LoggerFactory.getLogger(EligibilityServiceApplication.class);
  public static void main(String[] args) {
    log.info("Starting eligibility-service");
    var ctx = SpringApplication.run(EligibilityServiceApplication.class, args);
    log.info("Started eligibility-service");
  }
}
