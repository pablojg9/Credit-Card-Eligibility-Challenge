package com.cardplatform.proposal.adapter.in.web.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
    LocalDateTime timestamp,
    int status,
    String code,
    String message
) {
}
