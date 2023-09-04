package com.tc.training.cabrentals.facade.impl;

import java.time.LocalDateTime;
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
import com.tc.training.cabrentals.facade.CarFacade;
import com.tc.training.cabrentals.repositories.OrderRepository;
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
  private final OrderRepository orderRepository;

  @Override
  public CarOutput addCar( CarInput carInput ) {
    Car car = modelMapper.map( carInput, Car.class );
    final Center center = centerService.getById( carInput.getCenterId() );
    car.setCenter( center );
    car.setTripCount( 0 );
    car.setAverageRatings( 0.0 );
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
      Float minPrice, Float maxPrice, Gear gear, Integer tripCount, Double averageRatings, CarStatus status,
      FuelType fuelType, UUID centerId, LocalDateTime startDateTime, LocalDateTime dropDateTime ) {

    Page<Car> carPage = carService.getAllCars( pageNumber, pageSize, sortBy, sortDirection, query, type, model, seater,
        mileage, minPrice, maxPrice, gear, tripCount, averageRatings, status, fuelType, centerId, startDateTime,
        dropDateTime );
    PageOutput<CarOutput> carOutputPageOutput = AppUtils.convertPageToPageOutput( carPage, CarOutput.class );
    if( startDateTime != null && dropDateTime != null ) {
      List<Order> orders = orderRepository.findAll();
      for( int i = 0; i < orders.size(); i++ ) {
        Order order = orders.get( i );
        LocalDateTime ftime = order.getPickUpDate();
        LocalDateTime ttime = order.getReturnDate();
        boolean cond = true;
        if( ftime.equals( startDateTime ) || ftime.equals( dropDateTime ) ) {
          cond = false;
        } else if( ttime.equals( startDateTime ) || ttime.equals( dropDateTime ) ) {
          cond = false;
        } else if( startDateTime.isAfter( ftime ) && dropDateTime.isBefore( ftime ) ) {
          cond = false;
        } else if( startDateTime.isAfter( ttime ) && dropDateTime.isBefore( ttime ) ) {
          cond = false;
        }

        if( !cond ) {
          carOutputPageOutput.getContent().removeIf( carOutput -> carOutput.getId().equals( order.getCar().getId() ) );
        }
      }
    }

    return carOutputPageOutput;
  }

  @Override
  public CarOutput getCarById( final UUID id ) {
    Car output = carService.getCarById( id );
    return modelMapper.map( output, CarOutput.class );
  }
}
