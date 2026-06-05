package com.cardplatform.eligibility.adapter.out.persistence;

import com.cardplatform.eligibility.adapter.out.persistence.entity.BenefitRuleEntity;
import com.cardplatform.eligibility.adapter.out.persistence.entity.EligibilityRuleEntity;
import com.cardplatform.eligibility.adapter.out.persistence.repository.BenefitRuleRepository;
import com.cardplatform.eligibility.adapter.out.persistence.repository.EligibilityRuleRepository;
import com.cardplatform.eligibility.application.port.out.EligibilityRuleRepositoryOutPort;
import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.BenefitRule;
import com.cardplatform.eligibility.domain.model.EligibilityRule;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EligibilityRulePersistenceAdapter implements EligibilityRuleRepositoryOutPort {

  private final EligibilityRuleRepository eligibilityRuleRepository;
  private final BenefitRuleRepository benefitRuleRepository;

  public EligibilityRulePersistenceAdapter(
      final EligibilityRuleRepository eligibilityRuleRepository,
      final BenefitRuleRepository benefitRuleRepository) {
    this.eligibilityRuleRepository = eligibilityRuleRepository;
    this.benefitRuleRepository = benefitRuleRepository;
  }

  @Override
  public Optional<EligibilityRule> findOfferRuleByOfferType(final OfferType offerType) {
    return eligibilityRuleRepository.findByOfferTypeAndActiveTrue(offerType)
        .map(this::toDomain);
  }

  @Override
  public Optional<BenefitRule> findBenefitRuleByBenefitType(final BenefitType benefitType) {
    return benefitRuleRepository.findByBenefitTypeAndActiveTrue(benefitType)
        .map(this::toDomain);
  }

  private EligibilityRule toDomain(EligibilityRuleEntity entity) {
    return new EligibilityRule(
        entity.getOfferType(),
        entity.getMinIncome(),
        entity.getMinInvestments(),
        entity.getMinAccountYears(),
        entity.getActive()
    );
  }

  private BenefitRule toDomain(BenefitRuleEntity entity) {
    return new BenefitRule(
        entity.getBenefitType(),
        entity.getAllowedOffers(),
        entity.getActive()
    );
  }
}
