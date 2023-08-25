package com.tc.training.cabrentals.facade;

import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.dto.CenterTransferDto;
import com.tc.training.cabrentals.dto.PageOutput;

public interface CenterFacade {
  CenterOutput add( CenterInput centerInput );

  void delete( String id, CenterTransferDto centerTransferDto );

  PageOutput<CenterOutput> getAll( Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortDirection,
      String name, String city );
}
