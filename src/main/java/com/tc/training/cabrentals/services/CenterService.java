package com.tc.training.cabrentals.services;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.entities.Center;

public interface CenterService {
  Center add( Center center );

  Center centerById( UUID id );

  void deleteCenter( Center center );

  void deleteById( UUID id );

  List<Center> getAll();

}
