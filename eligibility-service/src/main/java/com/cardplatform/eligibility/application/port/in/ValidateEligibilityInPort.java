package com.cardplatform.eligibility.application.port.in;

import com.cardplatform.eligibility.adapter.in.web.EligibilityRequest;
import com.cardplatform.eligibility.adapter.in.web.EligibilityResponse;

public interface ValidateEligibilityInPort {

  EligibilityResponse validateEligibility(final EligibilityRequest request);

}
