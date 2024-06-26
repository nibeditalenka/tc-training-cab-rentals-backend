package com.tc.training.cabrentals.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;
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

  @PatchMapping( "{id}" )
  public CarOutput deleteCar( @PathVariable UUID id, @RequestParam CarStatus status ) {
    return carFacade.deleteCar( id, status );
  }

  @PutMapping( "/{id}" )
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
      @RequestParam( required = false ) Float maxPrice, @RequestParam( required = false ) Gear gear,
      @RequestParam( required = false ) Integer tripCount, @RequestParam( required = false ) Double averageRatings,
      @RequestParam( required = false, defaultValue = "AVAILABLE" ) CarStatus status,
      @RequestParam( required = false ) FuelType fuelType, @RequestParam( required = false ) UUID centerId,
      @RequestParam( required = false ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDateTime,
      @RequestParam( required = false ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dropDateTime ) {
    return carFacade.getAllCar( pageNumber, pageSize, sortBy, sortDirection, query, type, model, seater, mileage,
        minPrice, maxPrice, gear, tripCount, averageRatings, status, fuelType, centerId, startDateTime, dropDateTime );
  }

  @GetMapping( "/{id}" )
  public CarOutput getCarById( @PathVariable UUID id ) {
    return carFacade.getCarById( id );
  }
}
