package com.cardplatform.eligibility.application.port.out;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.BenefitRule;
import com.cardplatform.eligibility.domain.model.EligibilityRule;

import java.util.Optional;

public interface EligibilityRuleRepositoryOutPort {

  Optional<EligibilityRule> findOfferRuleByOfferType(final OfferType offerType);

  Optional<BenefitRule> findBenefitRuleByBenefitType(final BenefitType benefitType);
}
