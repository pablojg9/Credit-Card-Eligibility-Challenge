package com.cardplatform.eligibility.adapter.out.persistence.repository;

import com.cardplatform.eligibility.adapter.out.persistence.entity.EligibilityRuleEntity;
import com.cardplatform.eligibility.domain.enums.OfferType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EligibilityRuleRepository extends JpaRepository<EligibilityRuleEntity, UUID> {

  Optional<EligibilityRuleEntity> findByOfferTypeAndActiveTrue(final OfferType offerType);
}