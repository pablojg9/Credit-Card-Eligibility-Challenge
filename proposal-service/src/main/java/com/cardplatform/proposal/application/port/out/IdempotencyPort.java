package com.cardplatform.proposal.application.port.out;

import java.time.Duration;

public interface IdempotencyPort {
  boolean exists(final String key);

  void save(final String key, final Duration ttl);
}