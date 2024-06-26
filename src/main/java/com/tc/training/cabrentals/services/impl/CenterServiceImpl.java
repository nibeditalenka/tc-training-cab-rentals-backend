package com.tc.training.cabrentals.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.entities.QCenter;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;
import com.tc.training.cabrentals.repositories.CenterRepository;
import com.tc.training.cabrentals.services.CenterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
  private static final QCenter qCenter = QCenter.center;
  private final CenterRepository centerRepository;

  @Override
  public Center createOrUpdate( Center center ) {
    return centerRepository.save( center );
  }

  public Center getById( UUID id ) {
    return centerRepository.findById( id )
        .orElseThrow( () -> new ResourceNotFoundException( "center not found for this id" ) );
  }

  @Override
  public Page<Center> getAll( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final String name, final String city, final Boolean isActive ) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if( StringUtils.hasText( name ) ) {
      booleanBuilder.and( qCenter.name.startsWithIgnoreCase( name ) );
    }
    if( StringUtils.hasText( city ) ) {
      booleanBuilder.and( qCenter.address.city.startsWithIgnoreCase( city ) );
    }
    if( isActive != null ) {
      booleanBuilder.and( qCenter.isActive.eq( isActive ) );
    }
    PageRequest pageRequest = PageRequest.of( pageNumber, pageSize, Sort.by( sortDirection, sortBy ) );
    return centerRepository.findAll( booleanBuilder, pageRequest );
  }

}
