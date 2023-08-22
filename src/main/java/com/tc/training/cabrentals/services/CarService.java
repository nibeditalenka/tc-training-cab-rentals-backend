package com.tc.training.cabrentals.services;

import java.util.UUID;

import com.tc.training.cabrentals.entities.Car;

public interface CarService {
  Car createOrUpdate( Car car );

  Car getCarById( UUID id );
}
