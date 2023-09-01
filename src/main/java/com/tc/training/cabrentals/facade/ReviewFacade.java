package com.tc.training.cabrentals.facade;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.dto.ReviewInput;
import com.tc.training.cabrentals.dto.ReviewOutput;

public interface ReviewFacade {
  ReviewOutput create( ReviewInput input );

  List<ReviewOutput> getAllByFilter( final UUID carId, final UUID userId, final UUID orderId );
}
