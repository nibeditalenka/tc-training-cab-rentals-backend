package com.tc.training.cabrentals.services.impl;

import org.springframework.stereotype.Service;

import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.repositories.CarRepository;
import com.tc.training.cabrentals.services.CarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
  private final CarRepository carRepository;

  @Override
  public Car add( Car car ) {
    Car addedCar = carRepository.save( car );
    return car;
  }
}
