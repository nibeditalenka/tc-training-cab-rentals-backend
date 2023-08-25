package com.tc.training.cabrentals.facade;

import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.enums.CarStatus;

public interface CarFacade {
  CarOutput addCar( CarInput carInput );

  CarOutput deleteCar( String id, CarStatus carStatus );

  CarOutput updateCar( String id, CarInput carInput );

  PageOutput<CarOutput> getAllCar( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection,
      String query, String type, String model, String seater, String mileage, Float minPrice, Float maxPrice,
      Boolean automatic, Integer tripCount, Float averageRatings );

}
