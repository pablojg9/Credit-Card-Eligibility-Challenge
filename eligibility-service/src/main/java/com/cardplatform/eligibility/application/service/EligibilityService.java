package com.cardplatform.eligibility.application.service;

import com.cardplatform.eligibility.adapter.in.web.EligibilityRequest;
import com.cardplatform.eligibility.adapter.in.web.EligibilityResponse;

public interface EligibilityService {

  EligibilityResponse validate(final EligibilityRequest request);

}
