package com.cardplatform.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuditServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(AuditServiceApplication.class);
    public static void main(String[] args) {
        log.info("Starting audit-service");
        var ctx = SpringApplication.run(AuditServiceApplication.class, args);
        log.info("Started audit-service");
    }
}
