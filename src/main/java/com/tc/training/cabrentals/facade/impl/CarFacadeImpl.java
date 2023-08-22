package com.tc.training.cabrentals.facade.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.facade.CarFacade;
import com.tc.training.cabrentals.services.CarService;
import com.tc.training.cabrentals.services.CenterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CarFacadeImpl implements CarFacade {
  private final ModelMapper modelMapper;
  private final CarService carService;
  private final CenterService centerService;

  @Override
  public CarOutput addCar( CarInput carInput ) {
    Car car = modelMapper.map( carInput, Car.class );
    Center center = centerService.getById( carInput.getCenterId() );
    car.setCenter( center );
    car.setCarStatus( CarStatus.AVAILABLE );
    car.setTripCount( 0 );
    car = carService.createOrUpdate( car );
    return modelMapper.map( car, CarOutput.class );
  }

  @Override
  public CarOutput deleteCar( UUID id, CarStatus status ) {
    Car removeCar = carService.getCarById( id );
    removeCar.setCarStatus( status );
    carService.createOrUpdate( removeCar );
    return modelMapper.map( removeCar, CarOutput.class );
  }

  @Override
  public CarOutput updateCar( final UUID id, final CarInput carInput ) {
    Car updatedCar = carService.getCarById( id );
    updatedCar = modelMapper.map( carInput, Car.class );
    return modelMapper.map( updatedCar, CarOutput.class );
  }

}
