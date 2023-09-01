package com.tc.training.cabrentals.facade.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.facade.CarFacade;
import com.tc.training.cabrentals.services.CarService;
import com.tc.training.cabrentals.services.CenterService;
import com.tc.training.cabrentals.services.OrderService;
import com.tc.training.cabrentals.utils.AppUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CarFacadeImpl implements CarFacade {
  private final ModelMapper modelMapper;
  private final CarService carService;
  private final CenterService centerService;
  private final OrderService orderService;

  @Override
  public CarOutput addCar( CarInput carInput ) {
    Car car = modelMapper.map( carInput, Car.class );
    final Center center = centerService.getById( carInput.getCenterId() );
    car.setCenter( center );
    car.setTripCount( 0 );
    car.setAverageRatings( 0.0f );
    car.setCarStatus( CarStatus.AVAILABLE );
    car = carService.createOrUpdate( car );
    return modelMapper.map( car, CarOutput.class );
  }

  @Override
  public CarOutput deleteCar( UUID id, CarStatus carStatus ) {
    Car carUpdate = carService.getCarById( id );
    carUpdate.setCarStatus( carStatus );
    carService.createOrUpdate( carUpdate );
    return modelMapper.map( carUpdate, CarOutput.class );
  }

  @Override
  public CarOutput updateCar( UUID id, CarInput carInput ) {
    Car carUpdate = carService.getCarById( id );
    modelMapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT )
        .setPropertyCondition( Conditions.isNotNull() );
    modelMapper.map( carInput, carUpdate );
    carUpdate = carService.createOrUpdate( carUpdate );
    return modelMapper.map( carUpdate, CarOutput.class );
  }

  @Override
  public PageOutput<CarOutput> getAllCar( Integer pageNumber, Integer pageSize, String sortBy,
      Sort.Direction sortDirection, String query, String type, String model, String seater, String mileage,
      Float minPrice, Float maxPrice, Gear gear, Integer tripCount, Float averageRatings, CarStatus status,
      FuelType fuelType, UUID centerId, LocalDateTime startDateTime, LocalDateTime dropDateTime ) {

    Page<Car> carPage = carService.getAllCars( pageNumber, pageSize, sortBy, sortDirection, query, type, model, seater,
        mileage, minPrice, maxPrice, gear, tripCount, averageRatings, status, fuelType, centerId, startDateTime,
        dropDateTime );
    PageOutput<CarOutput> carOutputPageOutput = AppUtils.convertPageToPageOutput( carPage, CarOutput.class );
    List<CarOutput> content = carOutputPageOutput.getContent();
    List<CarOutput> op = new ArrayList<>();
    for( final CarOutput carOutput : content ) {
      List<Order> byCarId = orderService.getByCarId( carOutput.getId(), List.of( OrderStatus.RETURNED ) );
      boolean isConflict = false;
      for( final Order order : byCarId ) {
        isConflict = hasConflict( startDateTime, dropDateTime, order.getPickUpDate(), order.getReturnDate() );
      }
      if( !isConflict ) {
        op.add( carOutput );
      }
    }
    carOutputPageOutput.setContent( op );
    return carOutputPageOutput;
  }

  public boolean hasConflict( LocalDateTime startDateTime1, LocalDateTime endDateTime1, LocalDateTime startDateTime2,
      LocalDateTime endDateTime2 ) {
    return ( startDateTime1.isAfter( startDateTime2 ) && startDateTime1.isBefore(
        endDateTime2 ) ) || ( endDateTime1.isAfter( startDateTime2 ) && endDateTime1.isBefore(
        endDateTime2 ) ) || ( startDateTime1.isBefore( startDateTime2 ) && endDateTime1.isAfter( endDateTime2 ) );
  }

  @Override
  public CarOutput getCarById( final UUID id ) {
    Car output = carService.getCarById( id );
    return modelMapper.map( output, CarOutput.class );
  }
}
