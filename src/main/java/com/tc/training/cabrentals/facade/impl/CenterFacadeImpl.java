package com.tc.training.cabrentals.facade.impl;

import com.tc.training.cabrentals.dto.AddressInput;
import com.tc.training.cabrentals.entities.Address;
import com.tc.training.cabrentals.facade.CenterFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CenterFacadeImpl implements CenterFacade {
    private final ModelMapper modelMapper;
    @Override
    public void add(AddressInput addressInput) {
        Address address = modelMapper.map(addressInput, Address.class);

    }
}
