package com.tc.training.cabrentals.services;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.entities.Center;

public interface CenterService {
  Center createOrUpdate( Center center );

  Center getById( UUID id );

  List<Center> getAll();

}
