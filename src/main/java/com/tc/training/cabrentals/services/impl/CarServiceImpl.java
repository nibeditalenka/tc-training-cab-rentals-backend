package com.tc.training.cabrentals.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.QCar;
import com.tc.training.cabrentals.repositories.CarRepository;
import com.tc.training.cabrentals.services.CarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
  private static final QCar qCar = QCar.car;

  private final CarRepository carRepository;

  @Override
  public Car createOrUpdate( Car car ) {
    return carRepository.save( car );
  }

  @Override
  public Car getCarById( final String id ) {
    return carRepository.findById( id ).orElse( null );
  }

  @Override
  public Page<Car> getAllCars( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection,
      String query, String type, String model, String seater, String mileage, Float minPrice, Float maxPrice,
      Boolean automatic, Integer tripCount, Float averageRatings ) {

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if( StringUtils.hasText( type ) ) {
      booleanBuilder.and( qCar.type.startsWithIgnoreCase( type ) );
    }
    if( StringUtils.hasText( seater ) ) {
      booleanBuilder.and( qCar.seater.eq( seater ) );
    }
    if( StringUtils.hasText( mileage ) ) {
      booleanBuilder.and( qCar.mileage.eq( mileage ) );
    }
    if( minPrice != null && maxPrice != null ) {
      booleanBuilder.and( qCar.price.between( minPrice, maxPrice ) );
    }
    if( automatic != null ) {
      booleanBuilder.and( qCar.automatic.eq( automatic ) );
    }
    if( tripCount != null ) {
      booleanBuilder.and( qCar.tripCount.eq( tripCount ) );
    }
    if( averageRatings != null ) {
      booleanBuilder.and( qCar.averageRatings.goe( averageRatings ) );
    }

    final PageRequest pageRequest = PageRequest.of( pageNumber, pageSize, Sort.by( sortDirection, sortBy ) );
    return carRepository.findAll( booleanBuilder, pageRequest );
  }
}
