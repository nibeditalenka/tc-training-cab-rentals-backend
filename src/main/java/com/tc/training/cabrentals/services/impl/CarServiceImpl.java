package com.tc.training.cabrentals.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.QCar;
import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;
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
  public Car getCarById( final UUID id ) {
    return carRepository.findById( id ).orElse( null );
  }

  @Override
  public Page<Car> getAllCars( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection,
      String query, String type, String model, String seater, String mileage, Float minPrice, Float maxPrice, Gear gear,
      Integer tripCount, Float averageRatings, CarStatus status, FuelType fuelType, UUID centerId,
      LocalDateTime pickUpDateTime, LocalDateTime returnDateTime ) {

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
    if( gear != null ) {
      booleanBuilder.and( qCar.gear.eq( gear ) );
    }
    if( tripCount != null ) {
      booleanBuilder.and( qCar.tripCount.eq( tripCount ) );
    }
    if( averageRatings != null ) {
      booleanBuilder.and( qCar.averageRatings.goe( averageRatings ) );
    }
    if( centerId != null ) {
      booleanBuilder.and( qCar.center.id.eq( centerId ) );
    }
    if( fuelType != null ) {
      booleanBuilder.and( qCar.fuelType.eq( fuelType ) );
    }
    booleanBuilder.and( qCar.carStatus.eq( status ) );

    PageRequest pageRequest = PageRequest.of( pageNumber, pageSize, Sort.by( sortDirection, sortBy ) );
    return carRepository.findAll( booleanBuilder, pageRequest );
  }
}
