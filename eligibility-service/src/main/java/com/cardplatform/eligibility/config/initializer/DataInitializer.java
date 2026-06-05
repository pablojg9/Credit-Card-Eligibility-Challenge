package com.cardplatform.eligibility.config.initializer;

import com.cardplatform.eligibility.adapter.out.persistence.entity.BenefitRuleEntity;
import com.cardplatform.eligibility.adapter.out.persistence.entity.EligibilityRuleEntity;
import com.cardplatform.eligibility.adapter.out.persistence.repository.BenefitRuleRepository;
import com.cardplatform.eligibility.adapter.out.persistence.repository.EligibilityRuleRepository;
import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

  private final EligibilityRuleRepository eligibilityRuleJpaRepository;
  private final BenefitRuleRepository benefitRuleJpaRepository;

  public DataInitializer(
      final EligibilityRuleRepository eligibilityRuleJpaRepository,
      final BenefitRuleRepository benefitRuleJpaRepository) {
    this.eligibilityRuleJpaRepository = eligibilityRuleJpaRepository;
    this.benefitRuleJpaRepository = benefitRuleJpaRepository;
  }

  @Override
  public void run(String... args) {
    initOfferRules();
    initBenefitRules();
  }

  private void initOfferRules() {
    if (eligibilityRuleJpaRepository.count() > 0) {
      return;
    }

    eligibilityRuleJpaRepository.save(
        EligibilityRuleEntity.builder()
            .id(UUID.randomUUID())
            .offerType(OfferType.OFFER_A)
            .minIncome(new BigDecimal("1000"))
            .minInvestments(BigDecimal.ZERO)
            .minAccountYears(0)
            .active(true)
            .build()
    );

    eligibilityRuleJpaRepository.save(
        EligibilityRuleEntity.builder()
            .id(UUID.randomUUID())
            .offerType(OfferType.OFFER_B)
            .minIncome(new BigDecimal("15000"))
            .minInvestments(new BigDecimal("5000"))
            .minAccountYears(0)
            .active(true)
            .build()
    );

    eligibilityRuleJpaRepository.save(
        EligibilityRuleEntity.builder()
            .id(UUID.randomUUID())
            .offerType(OfferType.OFFER_C)
            .minIncome(new BigDecimal("50000"))
            .minInvestments(BigDecimal.ZERO)
            .minAccountYears(2)
            .active(true)
            .build()
    );
  }

  private void initBenefitRules() {
    if (benefitRuleJpaRepository.count() > 0) {
      return;
    }

    benefitRuleJpaRepository.save(
        BenefitRuleEntity.builder()
            .id(UUID.randomUUID())
            .benefitType(BenefitType.CASHBACK)
            .allowedOffers(Set.of(
                OfferType.OFFER_A,
                OfferType.OFFER_B,
                OfferType.OFFER_C
            ))
            .active(true)
            .build()
    );

    benefitRuleJpaRepository.save(
        BenefitRuleEntity.builder()
            .id(UUID.randomUUID())
            .benefitType(BenefitType.POINTS)
            .allowedOffers(Set.of(
                OfferType.OFFER_A,
                OfferType.OFFER_B,
                OfferType.OFFER_C
            ))
            .active(true)
            .build()
    );

    benefitRuleJpaRepository.save(
        BenefitRuleEntity.builder()
            .id(UUID.randomUUID())
            .benefitType(BenefitType.TRAVEL_INSURANCE)
            .allowedOffers(Set.of(
                OfferType.OFFER_C
            ))
            .active(true)
            .build()
    );

    benefitRuleJpaRepository.save(
        BenefitRuleEntity.builder()
            .id(UUID.randomUUID())
            .benefitType(BenefitType.VIP_LOUNGE)
            .allowedOffers(Set.of(
                OfferType.OFFER_B,
                OfferType.OFFER_C
            ))
            .active(true)
            .build()
    );
  }
}
