package com.tc.training.cabrentals.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.dto.CenterTransferDto;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.facade.CenterFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/centers" )
@RequiredArgsConstructor
class CenterController {
  private final CenterFacade centerFacade;

  @PostMapping
  public CenterOutput add( @RequestBody CenterInput centerInput ) {
    return centerFacade.add( centerInput );
  }

  @PatchMapping( "/{id}" )
  public void delete( @PathVariable String id, @RequestBody CenterTransferDto centerTransferDto ) {
    centerFacade.delete( id, centerTransferDto );
  }

  @GetMapping
  public PageOutput<CenterOutput> getAll( @RequestParam( required = false, defaultValue = "0" ) Integer pageNumber,
      @RequestParam( required = false, defaultValue = "25" ) Integer pageSize,
      @RequestParam( required = false, defaultValue = "name" ) String sortBy,
      @RequestParam( required = false, defaultValue = "ASC" ) Sort.Direction sortDirection,
      @RequestParam( required = false ) String name, @RequestParam( required = false ) String city ) {
    return centerFacade.getAll( pageNumber, pageSize, sortBy, sortDirection, name, city );
  }
}
