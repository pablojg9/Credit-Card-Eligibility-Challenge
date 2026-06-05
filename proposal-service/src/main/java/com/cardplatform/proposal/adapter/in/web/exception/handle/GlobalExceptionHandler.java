package com.cardplatform.proposal.adapter.in.web.exception.handle;

import com.cardplatform.proposal.adapter.in.web.dto.ApiErrorResponse;
import com.cardplatform.proposal.domain.exception.DuplicateProposalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateProposalException.class)
  public ResponseEntity<ApiErrorResponse> handleDuplicateProposal(
      DuplicateProposalException ex
  ) {

    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_CONTENT)
        .body(
            new ApiErrorResponse(
                LocalDateTime.now(),
                422,
                "DUPLICATE_PROPOSAL",
                ex.getMessage()
            )
        );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGeneric(
      Exception ex
  ) {

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new ApiErrorResponse(
                LocalDateTime.now(),
                500,
                "INTERNAL_SERVER_ERROR",
                "Erro interno ao processar a solicitação."
            )
        );
  }

}
