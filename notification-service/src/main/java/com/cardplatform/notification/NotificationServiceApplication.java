package com.cardplatform.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {
  private static final Logger log = LoggerFactory.getLogger(NotificationServiceApplication.class);
  public static void main(String[] args) {
    log.info("Starting notification-service");
    var ctx = SpringApplication.run(NotificationServiceApplication.class, args);
    log.info("Started notification-service");
  }
}
