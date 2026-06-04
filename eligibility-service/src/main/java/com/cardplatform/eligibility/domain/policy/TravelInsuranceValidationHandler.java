package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TravelInsuranceValidationHandler implements BenefitValidationHandler {
  public void validate(final OfferType offerType, final List<BenefitType> benefits) {
    if (benefits.contains(BenefitType.TRAVEL_INSURANCE) && offerType != OfferType.OFFER_C)
      throw new IllegalArgumentException("Seguro viagem disponível apenas para Oferta C.");
  }
}