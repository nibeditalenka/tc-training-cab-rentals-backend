package com.tc.training.cabrentals.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.CarInput;
import com.tc.training.cabrentals.dto.CarOutput;
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
}
