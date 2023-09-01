package com.tc.training.cabrentals.services;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.entities.Review;

public interface ReviewService {
  List<Review> getAllByFilter( final UUID carId, final UUID userId, final UUID orderId );

  Review createOrUpdate( Review review );

}
