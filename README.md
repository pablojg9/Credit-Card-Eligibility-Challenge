# Card Platform - Microservices

Projeto base em Java 21 + Spring Boot com arquitetura hexagonal, microserviços, WebClient, Kafka, PostgreSQL e Redis.

## Serviços

- proposal-service: orquestra a jornada de solicitação do cartão.
- eligibility-service: valida regras de oferta e benefícios usando Strategy + Chain of Responsibility.
- card-account-service: cria conta cartão.
- benefit-service: ativa benefícios.
- notification-service: consome Kafka e simula notificação.
- audit-service: consome Kafka e registra histórico/auditoria.

## Rodar com Docker

```bash
docker compose up --build
```

## Testar proposta aprovada

```bash
curl --location 'http://localhost:8080/proposal_service/api/v1/proposals' \
--header 'Content-Type: application/json' \
--data '{
    "cpf": "12345678159",
    "name": "Pablo Junior",
    "income": 16000,
    "investments": 6000,
    "checkingAccountCreatedAt": "2025-01-01",
    "offerType": "OFFER_B",
    "selectedBenefits": [
        "VIP_LOUNGE"
    ]
}'
```

## Testar proposta recusada

```bash
curl --location 'http://localhost:8080/proposal_service/api/v1/proposals' \
--header 'Content-Type: application/json' \
--data '{
    "cpf": "12345678144",
    "name": "Pablo Junior",
    "income": 3000,
    "investments": 0,
    "checkingAccountCreatedAt": "2025-01-01",
    "offerType": "OFFER_B",
    "selectedBenefits": [
        "VIP_LOUNGE"
    ]
}'
```

## Observações

- Redis está sendo usado no proposal-service para idempotência, evitando propostas duplicadas em curto período.
- Kafka publica eventos da jornada: received, rejected e completed.
- Dados sensíveis como CPF são mascarados no audit-service.
- Cada microserviço tem seu próprio banco PostgreSQL.
