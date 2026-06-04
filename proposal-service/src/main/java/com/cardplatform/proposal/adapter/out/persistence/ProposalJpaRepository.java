package com.cardplatform.proposal.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalJpaRepository extends JpaRepository<ProposalEntity, UUID> {
}