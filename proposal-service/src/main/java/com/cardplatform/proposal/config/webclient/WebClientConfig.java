package com.cardplatform.proposal.config.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean
  WebClient eligibilityWebClient(@Value("${services.eligibility.url}") final String url) {
    return WebClient.builder()
        .baseUrl(url)
        .build();
  }

  @Bean
  WebClient cardAccountWebClient(@Value("${services.card-account.url}") final String url) {
    return WebClient.builder()
        .baseUrl(url)
        .build();
  }

  @Bean
  WebClient benefitWebClient(@Value("${services.benefit.url}") final String url) {
    return WebClient.builder()
        .baseUrl(url)
        .build();
  }
}