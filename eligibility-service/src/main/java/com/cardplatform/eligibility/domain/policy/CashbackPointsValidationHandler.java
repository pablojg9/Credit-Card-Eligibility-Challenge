package com.cardplatform.eligibility.domain.policy;

import com.cardplatform.eligibility.domain.enums.BenefitType;
import com.cardplatform.eligibility.domain.enums.OfferType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CashbackPointsValidationHandler implements BenefitValidationHandler {
  public void validate(final OfferType offerType, final List<BenefitType> benefits) {
    if (benefits.contains(BenefitType.CASHBACK) && benefits.contains(BenefitType.POINTS))
      throw new IllegalArgumentException("Cashback e pontos não podem ser escolhidos juntos.");
  }
}