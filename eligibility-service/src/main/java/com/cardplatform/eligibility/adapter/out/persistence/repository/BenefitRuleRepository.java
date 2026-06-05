package com.cardplatform.eligibility.adapter.out.persistence.repository;

import com.cardplatform.eligibility.adapter.out.persistence.entity.BenefitRuleEntity;
import com.cardplatform.eligibility.domain.enums.BenefitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BenefitRuleRepository extends JpaRepository<BenefitRuleEntity, UUID> {

  Optional<BenefitRuleEntity> findByBenefitTypeAndActiveTrue(final BenefitType benefitType);

}
