package com.tc.training.cabrentals.facade.impl;

import java.util.List;
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
    final Center center = centerService.getById( carInput.getCenterId() );
    car.setCenter( center );
    car.setTripCount( 0 );
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
    carUpdate = modelMapper.map( carInput, Car.class );
    carService.createOrUpdate( carUpdate );
    return modelMapper.map( carUpdate, CarOutput.class );
  }

  @Override
  public List<Car> getAllCar() {
    return carService.getAllCars();
  }

}
