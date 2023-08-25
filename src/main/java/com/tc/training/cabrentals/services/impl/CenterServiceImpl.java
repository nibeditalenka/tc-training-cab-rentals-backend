package com.tc.training.cabrentals.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.entities.QCenter;
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

  public Center getById( String id ) {
    return centerRepository.findById( id ).orElse( ( null ) );
  }

  @Override
  public Page<Center> getAll( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final String name, final String city ) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if( StringUtils.hasText( name ) ) {
      booleanBuilder.and( qCenter.name.startsWithIgnoreCase( name ) );
    }
    if( StringUtils.hasText( city ) ) {
      booleanBuilder.and( qCenter.address.city.startsWithIgnoreCase( city ) );
    }
    final PageRequest pageRequest = PageRequest.of( pageNumber, pageSize, Sort.by( sortDirection, sortBy ) );
    return centerRepository.findAll( booleanBuilder, pageRequest );
  }

}
