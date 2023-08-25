package com.tc.training.cabrentals.controller;

import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.facade.CarFacade;

import lombok.RequiredArgsConstructor;

@RequestMapping( "/cars" )
@RestController
@RequiredArgsConstructor
public class CarController {

  private final CarFacade carFacade;

  @PostMapping
  public CarOutput addCar( @RequestBody CarInput carInput ) {
    return carFacade.addCar( carInput );
  }

  @PatchMapping( "update-status/{id}" )
  public CarOutput deleteCar( @PathVariable UUID id, @RequestParam CarStatus status ) {
    return carFacade.deleteCar( id, status );
  }

  @PutMapping( "update-car/{id}" )
  public CarOutput updateCar( @PathVariable UUID id, @RequestBody CarInput carInput ) {
    return carFacade.updateCar( id, carInput );
  }

  @GetMapping
  public PageOutput<CarOutput> getAllCar( @RequestParam( required = false, defaultValue = "0" ) Integer pageNumber,
      @RequestParam( required = false, defaultValue = "25" ) Integer pageSize,
      @RequestParam( required = false, defaultValue = "model" ) String sortBy,
      @RequestParam( required = false, defaultValue = "ASC" ) Sort.Direction sortDirection,
      @RequestParam( required = false ) String query, @RequestParam( required = false ) String type,
      @RequestParam( required = false ) String model, @RequestParam( required = false ) String seater,
      @RequestParam( required = false ) String mileage, @RequestParam( required = false ) Float minPrice,
      @RequestParam( required = false ) Float maxPrice, @RequestParam( required = false ) Boolean automatic,
      @RequestParam( required = false ) Integer tripCount, @RequestParam( required = false ) Float averageRatings ) {
    return carFacade.getAllCar( pageNumber, pageSize, sortBy, sortDirection, query, type, model, seater, mileage,
        minPrice, maxPrice, automatic, tripCount, averageRatings );
  }
}
