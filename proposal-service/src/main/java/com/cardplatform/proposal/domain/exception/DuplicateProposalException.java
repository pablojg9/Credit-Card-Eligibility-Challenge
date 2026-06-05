package com.cardplatform.proposal.domain.exception;

public class DuplicateProposalException extends RuntimeException {
  public DuplicateProposalException(final String message) {
    super(message);
  }
}
