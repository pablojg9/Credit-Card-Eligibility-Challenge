package com.cardplatform.benefit.application.service;

import com.cardplatform.benefit.adapter.in.web.BenefitActivationRequest;
import com.cardplatform.benefit.adapter.in.web.BenefitActivationResponse;
import com.cardplatform.benefit.adapter.out.persistence.BenefitEntity;
import com.cardplatform.benefit.adapter.out.persistence.BenefitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BenefitService {
  private final BenefitRepository benefitRepository;

  public BenefitService(final BenefitRepository benefitRepository) {
    this.benefitRepository = benefitRepository;
  }

  public BenefitActivationResponse activate(final BenefitActivationRequest request) {
    request.benefits().forEach(benefitType -> {
      var benefitEntity = new BenefitEntity();
      benefitEntity.id = UUID.randomUUID();
      benefitEntity.proposalId = request.proposalId();
      benefitEntity.cardAccountId = request.cardAccountId();
      benefitEntity.benefitType = benefitType;
      benefitEntity.status = "ACTIVE";
      benefitEntity.activatedAt = LocalDateTime.now();
      benefitRepository.save(benefitEntity);
    });
    return new BenefitActivationResponse(request.benefits());
  }
}