package com.cardplatform.cardaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardAccountServiceApplication {
  private static final Logger log = LoggerFactory.getLogger(CardAccountServiceApplication.class);
  public static void main(String[] args) {
    log.info("Starting card-account-service");
    var ctx = SpringApplication.run(CardAccountServiceApplication.class, args);
    log.info("Started card-account-service");
  }
}
