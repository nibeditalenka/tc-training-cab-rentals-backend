package com.tc.training.cabrentals.facade;

import java.util.UUID;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.enums.CarStatus;

public interface CarFacade {
  CarOutput addCar( CarInput carInput );

  CarOutput deleteCar( UUID id, CarStatus carStatus );

  CarOutput updateCar( UUID id, CarInput carInput );

}
