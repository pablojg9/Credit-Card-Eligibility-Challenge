package com.cardplatform.eligibility.application.usecase;

import com.cardplatform.eligibility.adapter.in.web.EligibilityRequest;
import com.cardplatform.eligibility.adapter.in.web.EligibilityResponse;
import com.cardplatform.eligibility.application.port.in.ValidateEligibilityInPort;
import com.cardplatform.eligibility.application.port.out.EligibilityRuleCacheOutPort;
import com.cardplatform.eligibility.application.port.out.EligibilityRuleRepositoryOutPort;
import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import com.cardplatform.eligibility.domain.model.BenefitRule;
import com.cardplatform.eligibility.domain.model.EligibilityRule;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidateEligibilityUseCase implements ValidateEligibilityInPort {
  private static final Duration CACHE_TTL = Duration.ofMinutes(30);

  private final EligibilityRuleRepositoryOutPort repositoryPort;
  private final EligibilityRuleCacheOutPort cachePort;

  public ValidateEligibilityUseCase(
      final EligibilityRuleRepositoryOutPort repositoryPort,
      final EligibilityRuleCacheOutPort cachePort) {
    this.repositoryPort = repositoryPort;
    this.cachePort = cachePort;
  }

  @Override
  public EligibilityResponse validateEligibility(final EligibilityRequest request) {

    final EligibilityRule offerRule = findOfferRule(request.offerType());

    if (!isEligibleForOffer(request, offerRule)) {
      return EligibilityResponse.rejected(
          "Cliente não elegível para a oferta selecionada.",
          request.selectedBenefits()
      );
    }

    if (request.selectedBenefits().contains(BenefitType.CASHBACK)
        && request.selectedBenefits().contains(BenefitType.POINTS)) {
      return EligibilityResponse.rejected(
          "Cashback e Pontos não podem ser selecionados juntos.",
          request.selectedBenefits()
      );
    }

    final List<BenefitType> activated = new ArrayList<>();
    final List<BenefitType> rejected = new ArrayList<>();


    for (BenefitType benefitType : request.selectedBenefits()) {
      BenefitRule benefitRule = findBenefitRule(benefitType);

      if (benefitRule.allowedOffers().contains(request.offerType())) {
        activated.add(benefitType);
      } else {
        rejected.add(benefitType);
      }
    }

    if (!rejected.isEmpty()) {
      return EligibilityResponse.rejected(
          "Existem benefícios não elegíveis para a oferta selecionada.",
          rejected
      );
    }

    return EligibilityResponse.approved(activated);
  }

  private EligibilityRule findOfferRule(final OfferType offerType) {
    return cachePort.findOfferRule(offerType)
        .orElseGet(() -> {
          final EligibilityRule rule = repositoryPort.findOfferRuleByOfferType(offerType)
              .orElseThrow(() -> new IllegalArgumentException("Regra de oferta não encontrada."));

          cachePort.saveOfferRule(rule, CACHE_TTL);

          return rule;
        });
  }

  private BenefitRule findBenefitRule(BenefitType benefitType) {
    return cachePort.findBenefitRule(benefitType)
        .orElseGet(() -> {
          final BenefitRule rule = repositoryPort.findBenefitRuleByBenefitType(benefitType)
              .orElseThrow(() -> new IllegalArgumentException("Regra de benefício não encontrada."));

          cachePort.saveBenefitRule(rule, CACHE_TTL);

          return rule;
        });
  }

  private boolean isEligibleForOffer(
      final EligibilityRequest request,
      final EligibilityRule eligibilityRule
  ) {
    boolean incomeOk = request.income().compareTo(eligibilityRule.minIncome()) > 0;

    boolean investmentsOk = request.investments().compareTo(eligibilityRule.minInvestments()) > 0
        || eligibilityRule.minInvestments().signum() == 0;

    boolean accountTimeOk = eligibilityRule.minAccountYears() == 0
        || request.checkingAccountCreatedAt()
        .isBefore(LocalDate.now().minusYears(eligibilityRule.minAccountYears()));

    return incomeOk && investmentsOk && accountTimeOk;
  }
}
