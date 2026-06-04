package com.cardplatform.eligibility.application.service;

import com.cardplatform.eligibility.adapter.in.web.EligibilityRequest;
import com.cardplatform.eligibility.adapter.in.web.EligibilityResponse;
import com.cardplatform.eligibility.domain.policy.BenefitValidationHandler;
import com.cardplatform.eligibility.domain.policy.OfferEligibilityStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityServiceImpl implements EligibilityService {
  private final OfferEligibilityStrategyFactory offerEligibilityStrategyFactory;
  private final List<BenefitValidationHandler> benefitValidationHandlers;

  public EligibilityServiceImpl(
      final OfferEligibilityStrategyFactory offerEligibilityStrategyFactory,
      final List<BenefitValidationHandler> benefitValidationHandlers) {
    this.offerEligibilityStrategyFactory = offerEligibilityStrategyFactory;
    this.benefitValidationHandlers = benefitValidationHandlers;
  }

  @Override
  public EligibilityResponse validate(final EligibilityRequest request) {
    final boolean ok = offerEligibilityStrategyFactory.get(request.offerType()).isEligible(request.customer());
    if (!ok) return EligibilityResponse.rejected("Cliente não elegível para a oferta selecionada.");
    try {
      benefitValidationHandlers.forEach(benefitValidationHandler -> benefitValidationHandler
          .validate(request.offerType(), request.selectedBenefits())
      );
    } catch (IllegalArgumentException e) {
      return EligibilityResponse.rejected(e.getMessage());
    }
    return EligibilityResponse.approved(request.selectedBenefits());
  }
}