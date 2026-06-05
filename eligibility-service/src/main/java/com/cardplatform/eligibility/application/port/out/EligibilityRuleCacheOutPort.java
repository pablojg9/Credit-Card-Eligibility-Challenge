package com.cardplatform.eligibility.application.port.out;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.BenefitRule;
import com.cardplatform.eligibility.domain.model.EligibilityRule;

import java.time.Duration;
import java.util.Optional;

public interface EligibilityRuleCacheOutPort {

  Optional<EligibilityRule> findOfferRule(final OfferType offerType);

  void saveOfferRule(final EligibilityRule eligibilityRule, final Duration ttlDuration);

  Optional<BenefitRule> findBenefitRule(final BenefitType benefitType);

  void saveBenefitRule(final BenefitRule benefitRule, final Duration ttlDuration);

}
