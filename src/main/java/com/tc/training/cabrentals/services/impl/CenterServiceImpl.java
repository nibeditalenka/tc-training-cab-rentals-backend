package com.tc.training.cabrentals.services.impl;

import com.tc.training.cabrentals.entities.Address;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.repositories.CenterRepository;
import com.tc.training.cabrentals.services.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;
    @Override
    public Center add(Address address) {
        Center center = new Center();
        center.setAddress(address);
        centerRepository.save(center);
        return center;
    }
}
