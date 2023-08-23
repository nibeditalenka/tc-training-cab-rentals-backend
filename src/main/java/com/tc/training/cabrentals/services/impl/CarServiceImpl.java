package com.tc.training.cabrentals.services.impl;

import java.util.List;
import java.util.UUID;

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
  public Car createOrUpdate( Car car ) {
    Car addedCar = carRepository.save( car );
    return car;
  }

  @Override
  public Car getCarById( final UUID id ) {
    return carRepository.findById( id ).orElse( null );
  }

  @Override
  public List<Car> getAllCars() {
    return (List<Car>) carRepository.findAll();
  }
}
