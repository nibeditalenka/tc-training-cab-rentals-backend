package com.tc.training.cabrentals.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.QReview;
import com.tc.training.cabrentals.entities.Review;
import com.tc.training.cabrentals.repositories.ReviewRepository;
import com.tc.training.cabrentals.services.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private static final QReview qReview = QReview.review;
  private final ReviewRepository reviewRepository;

  @Override
  public List<Review> getAllByFilter( final UUID carId, final UUID userId, final UUID orderId ) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if( carId != null ) {
      booleanBuilder.and( qReview.order.car.id.eq( carId ) );
    }
    if( userId != null ) {
      booleanBuilder.and( qReview.user.id.eq( userId ) );
    }
    if( orderId != null ) {
      booleanBuilder.and( qReview.order.id.eq( orderId ) );
    }
    return (List<Review>) reviewRepository.findAll( booleanBuilder );
  }

  @Override
  public Review createOrUpdate( final Review review ) {
    return reviewRepository.save( review );
  }
}
