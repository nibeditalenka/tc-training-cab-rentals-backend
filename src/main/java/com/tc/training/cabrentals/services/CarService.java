package com.tc.training.cabrentals.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;

public interface CarService {
  Car createOrUpdate( Car car );

  Car getCarById( UUID id );

  Page<Car> getAllCars( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection, String query,
      String type, String model, String seater, String mileage, Float minPrice, Float maxPrice, Gear gear,
      Integer tripCount, Double averageRatings, CarStatus status, FuelType fuelType, UUID centerId,
      final LocalDateTime startDateTime, final LocalDateTime dropDateTime );
}
