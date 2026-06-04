package com.cardplatform.benefit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BenefitServiceApplication {
  private static final Logger log = LoggerFactory.getLogger(BenefitServiceApplication.class);
  public static void main(String[] args) {
    log.info("Starting benefit-service");
    var ctx = SpringApplication.run(BenefitServiceApplication.class, args);
    log.info("Started benefit-service");
  }
}
