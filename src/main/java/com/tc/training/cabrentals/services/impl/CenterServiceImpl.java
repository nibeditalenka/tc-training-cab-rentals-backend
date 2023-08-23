package com.tc.training.cabrentals.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.repositories.CenterRepository;
import com.tc.training.cabrentals.services.CenterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
  private final CenterRepository centerRepository;

  @Override
  public Center createOrUpdate( Center center ) {
    return centerRepository.save( center );
  }

  public Center getById( UUID id ) {
    return centerRepository.findById( id ).orElse( ( null ) );
  }

  @Override
  public List<Center> getAll() {
    return (List<Center>) centerRepository.findAll();
  }
}
