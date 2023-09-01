package com.tc.training.cabrentals.facade;

import java.util.UUID;

import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.dto.CenterTransferDto;
import com.tc.training.cabrentals.dto.PageOutput;

public interface CenterFacade {
  CenterOutput add( CenterInput centerInput );

  void delete( UUID id, CenterTransferDto centerTransferDto );

  PageOutput<CenterOutput> getAll( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection,
      String name, String city, final Boolean isActive );

  CenterOutput getById( UUID id );

  CenterOutput updateCenter( UUID id, CenterInput centerInput );
}
