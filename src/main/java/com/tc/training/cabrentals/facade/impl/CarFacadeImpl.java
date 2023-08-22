package com.tc.training.cabrentals.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Center;
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
    final Center center = centerService.centerById( carInput.getCenterId() );
    car.setCenter( center );
    car.setTripCount( 0 );
    car = carService.add( car );
    return modelMapper.map( car, CarOutput.class );
  }

}
