package com.tc.training.cabrentals.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.entities.Car;

public interface CarService {
  Car createOrUpdate( Car car );

  Car getCarById( String id );

  Page<Car> getAllCars( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection, String query,
      String type, String model, String seater, String mileage, Float minPrice, Float maxPrice, Boolean automatic,
      Integer tripCount, Float averageRatings );
}
