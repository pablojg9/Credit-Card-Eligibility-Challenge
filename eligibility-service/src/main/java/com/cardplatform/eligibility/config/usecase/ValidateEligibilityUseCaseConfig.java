package com.cardplatform.eligibility.config.usecase;

import com.cardplatform.eligibility.application.port.in.ValidateEligibilityInPort;
import com.cardplatform.eligibility.application.port.out.EligibilityRuleCacheOutPort;
import com.cardplatform.eligibility.application.port.out.EligibilityRuleRepositoryOutPort;
import com.cardplatform.eligibility.application.usecase.ValidateEligibilityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateEligibilityUseCaseConfig {

  @Bean
  public ValidateEligibilityInPort validateEligibilityInPort(
      final EligibilityRuleRepositoryOutPort eligibilityRuleRepositoryOutPort,
      final EligibilityRuleCacheOutPort eligibilityRuleCacheOutPort) {
    return new ValidateEligibilityUseCase(eligibilityRuleRepositoryOutPort, eligibilityRuleCacheOutPort);
  }

}
