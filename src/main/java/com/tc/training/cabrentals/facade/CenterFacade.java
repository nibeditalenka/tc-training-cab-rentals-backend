package com.tc.training.cabrentals.facade;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.dto.CenterTransferDto;
import com.tc.training.cabrentals.entities.Center;

public interface CenterFacade {
  CenterOutput add( CenterInput centerInput );

  void delete( UUID id, CenterTransferDto centerTransferDto );

  List<Center> getAll();

}
