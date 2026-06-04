package com.cardplatform.cardaccount.adapter.in.web;

import java.util.UUID;

public record CardAccountRequest(
    UUID id,
    String cpf,
    String offerType
) {
}