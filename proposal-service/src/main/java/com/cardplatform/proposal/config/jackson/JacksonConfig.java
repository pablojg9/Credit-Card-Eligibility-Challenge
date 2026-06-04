package com.cardplatform.proposal.config.jackson;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public JavaTimeModule javaTimeModule() {
    return new JavaTimeModule();
  }

  @Bean
  public com.fasterxml.jackson.databind.ObjectMapper objectMapper(final JavaTimeModule javaTimeModule) {
    final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    mapper.registerModule(javaTimeModule);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }
}

