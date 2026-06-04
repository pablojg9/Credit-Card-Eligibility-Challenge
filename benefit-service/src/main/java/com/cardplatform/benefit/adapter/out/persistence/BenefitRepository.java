package com.cardplatform.benefit.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BenefitRepository extends JpaRepository<BenefitEntity, UUID> {
}