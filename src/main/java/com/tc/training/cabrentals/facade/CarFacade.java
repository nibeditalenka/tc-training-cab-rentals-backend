package com.tc.training.cabrentals.facade;

import java.util.UUID;

import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;

public interface CarFacade {
  CarOutput addCar( CarInput carInput );

  CarOutput deleteCar( UUID id, CarStatus carStatus );

  CarOutput updateCar( UUID id, CarInput carInput );

  PageOutput<CarOutput> getAllCar( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection,
      String query, String type, String model, String seater, String mileage, Float minPrice, Float maxPrice, Gear gear,
      Integer tripCount, Float averageRatings, CarStatus status, FuelType fuelType, UUID centerId );

  CarOutput getCarById( UUID id );
}
