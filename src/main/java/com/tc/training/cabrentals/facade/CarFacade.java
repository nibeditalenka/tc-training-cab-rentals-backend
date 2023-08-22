package com.tc.training.cabrentals.facade;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;

public interface CarFacade {
  CarOutput addCar( CarInput carInput );
}
