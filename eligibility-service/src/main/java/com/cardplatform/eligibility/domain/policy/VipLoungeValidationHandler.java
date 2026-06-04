package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VipLoungeValidationHandler implements BenefitValidationHandler {
  public void validate(final OfferType offerType, final List<BenefitType> benefits) {
    if (benefits.contains(BenefitType.VIP_LOUNGE) && offerType == OfferType.OFFER_A)
      throw new IllegalArgumentException("Sala VIP disponível apenas para Oferta B e C.");
  }
}