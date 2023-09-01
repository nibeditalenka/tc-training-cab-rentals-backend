package com.tc.training.cabrentals.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.ReviewInput;
import com.tc.training.cabrentals.dto.ReviewOutput;
import com.tc.training.cabrentals.facade.ReviewFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "reviews" )
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewFacade reviewFacade;

  @GetMapping
  public List<ReviewOutput> getAll( @RequestParam( required = false ) UUID carId,
      @RequestParam( required = false ) UUID userId, @RequestParam( required = false ) UUID orderId ) {
    return reviewFacade.getAllByFilter( carId, userId, orderId );
  }

  @PostMapping
  public ReviewOutput create( @RequestBody ReviewInput input ) {
    return reviewFacade.create( input );
  }
}
