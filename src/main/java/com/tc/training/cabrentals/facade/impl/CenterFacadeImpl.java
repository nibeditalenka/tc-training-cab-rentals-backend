package com.tc.training.cabrentals.facade.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.dto.CenterTransferDto;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.entities.Address;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.facade.CenterFacade;
import com.tc.training.cabrentals.services.CenterService;
import com.tc.training.cabrentals.utils.AppUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CenterFacadeImpl implements CenterFacade {
  private final ModelMapper modelMapper;
  private final CenterService centerService;

  @Override
  public CenterOutput add( CenterInput centerInput ) {
    Center center = new Center();
    center.setName( centerInput.getName() );

    Address address = new Address();
    address.setAddressLine1( centerInput.getAddress().getAddressLine1() );
    address.setAddressLine2( centerInput.getAddress().getAddressLine2() );
    address.setLandMark( centerInput.getAddress().getLandMark() );
    address.setPinCode( centerInput.getAddress().getPinCode() );
    address.setCity( centerInput.getAddress().getCity() );

    center.setAddress( address );
    Center newCenter = centerService.createOrUpdate( center );
    final CenterOutput output = modelMapper.map( newCenter, CenterOutput.class );
    return output;
  }

  @Override
  public void delete( UUID id, CenterTransferDto centerTransferDto ) {
    Center centerToBeDeleted = centerService.getById( id );
    if( centerTransferDto.getIsTransferringCars().equals( Boolean.TRUE ) ) {
      Center toCenter = centerService.getById( centerTransferDto.getCenterId() );
      if( CollectionUtils.isEmpty( toCenter.getCars() ) ) {
        toCenter.setCars( new ArrayList<>() );
      }
      toCenter.getCars().addAll( centerToBeDeleted.getCars() );
      centerService.createOrUpdate( toCenter );
    }
    centerToBeDeleted.setIsActive( Boolean.FALSE );
    centerService.createOrUpdate( centerToBeDeleted );
  }

  @Override
  public PageOutput<CenterOutput> getAll( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final String name, final String city ) {

    Page<Center> centerPage = centerService.getAll( pageNumber, pageSize, sortBy, sortDirection, name, city );
    return AppUtils.convertPageToPageOutput( centerPage, CenterOutput.class );
  }

  @Override
  public CenterOutput getById( final UUID id ) {
    Center center = centerService.getById( id );
    return modelMapper.map( center, CenterOutput.class );
  }

  @Override
  public CenterOutput updateCenter( UUID id, CenterInput centerInput ) {
    Center center = centerService.getById( id );
    modelMapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT )
        .setPropertyCondition( Conditions.isNotNull() );
    modelMapper.map( centerInput, center );
    center = centerService.createOrUpdate( center );
    return modelMapper.map( center, CenterOutput.class );
  }

}
