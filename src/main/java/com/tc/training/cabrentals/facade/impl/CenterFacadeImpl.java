package com.tc.training.cabrentals.facade.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.entities.Address;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.facade.CenterFacade;
import com.tc.training.cabrentals.services.CenterService;

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
    Center newCenter = centerService.add( center );
    final CenterOutput output = modelMapper.map( newCenter, CenterOutput.class );
    return output;
  }

  @Override
  public void delete( UUID id ) {
    centerService.deleteById( id );
  }

  @Override
  public List<Center> getAll() {
    return centerService.getAll();
  }
}
