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
curl -X POST http://localhost:8080/proposals \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pablo Junior",
    "cpf": "12345678901",
    "income": 20000,
    "investments": 6000,
    "checkingAccountCreatedAt": "2022-01-01",
    "offerType": "OFFER_B",
    "selectedBenefits": ["VIP_LOUNGE", "POINTS"]
  }'
```

## Testar proposta recusada

```bash
curl -X POST http://localhost:8080/proposals \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Cliente Teste",
    "cpf": "99999999999",
    "income": 900,
    "investments": 0,
    "checkingAccountCreatedAt": "2025-01-01",
    "offerType": "OFFER_A",
    "selectedBenefits": ["CASHBACK"]
  }'
```

## Observações

- Redis está sendo usado no proposal-service para idempotência, evitando propostas duplicadas em curto período.
- Kafka publica eventos da jornada: received, rejected e completed.
- Dados sensíveis como CPF são mascarados no audit-service.
- Cada microserviço tem seu próprio banco PostgreSQL.
