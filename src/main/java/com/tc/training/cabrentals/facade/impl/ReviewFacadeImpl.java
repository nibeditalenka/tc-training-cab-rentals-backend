package com.tc.training.cabrentals.facade.impl;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.ReviewInput;
import com.tc.training.cabrentals.dto.ReviewOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.entities.Review;
import com.tc.training.cabrentals.exception.BusinessException;
import com.tc.training.cabrentals.facade.ReviewFacade;
import com.tc.training.cabrentals.services.CarService;
import com.tc.training.cabrentals.services.OrderService;
import com.tc.training.cabrentals.services.ReviewService;
import com.tc.training.cabrentals.utils.CurrentUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class ReviewFacadeImpl implements ReviewFacade {
  private final ReviewService reviewService;
  private final OrderService orderService;
  private final ModelMapper modelMapper;
  private final CarService carService;

  @Override
  public ReviewOutput create( final ReviewInput input ) {
    Order order = orderService.getById( input.getOrderId() )
        .orElseThrow( () -> new BusinessException( "Order not found with id " + input.getOrderId() ) );
    Review review = modelMapper.map( input, Review.class );
    review.setUser( CurrentUser.get() );
    review.setOrder( order );
    review = reviewService.createOrUpdate( review );
    order.setReview( review );
    updateCarAverageRatings( order.getCar() );
    order = orderService.createOrUpdate( order );
    review = reviewService.createOrUpdate( review );
    return modelMapper.map( review, ReviewOutput.class );
  }

  @Override
  public List<ReviewOutput> getAllByFilter( final UUID carId, final UUID userId, final UUID orderId ) {
    return reviewService.getAllByFilter( carId, userId, orderId ).stream()
        .map( review -> modelMapper.map( review, ReviewOutput.class ) ).toList();
  }

  private void updateCarAverageRatings( Car car ) {
    List<Order> orders = orderService.getByCarId( car.getId() );

    OptionalDouble averageRating = orders.stream()
        .mapToDouble( order -> Optional.ofNullable( order.getReview() ).map( Review::getRating ).orElse( 0 ) )
        .filter( rating -> rating > 0 ).average();

    long reviewCount = orders.stream().map( Order::getReview )
        .filter( review -> review != null && review.getRating() != null && review.getRating() > 0 ).count();

    double average = averageRating.orElse( 0.0 );
    car.setAverageRatings( average );
    car.setNumberOfRatings( reviewCount );
    carService.createOrUpdate( car );
  }
}
