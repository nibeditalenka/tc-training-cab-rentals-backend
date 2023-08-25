package com.tc.training.cabrentals.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.entities.Center;

public interface CenterService {
  Center createOrUpdate( Center center );

  Center getById( UUID id );

  Page<Center> getAll( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection, String name,
      String city );
}
