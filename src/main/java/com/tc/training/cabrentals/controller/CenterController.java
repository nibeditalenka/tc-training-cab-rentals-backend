package com.tc.training.cabrentals.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.facade.CenterFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/center" )
@RequiredArgsConstructor
class CenterController {
  private final CenterFacade centerFacade;

  @PostMapping
  public CenterOutput add( @RequestBody CenterInput centerInput ) {
    return centerFacade.add( centerInput );
  }

  @DeleteMapping( "/{id}" )
  public void delete( @PathVariable UUID id ) {
    centerFacade.delete( id );
  }

  @GetMapping
  public List<Center> getAll() {
    return centerFacade.getAll();
  }
}
