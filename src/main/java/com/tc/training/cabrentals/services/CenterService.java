package com.tc.training.cabrentals.services;

import com.tc.training.cabrentals.entities.Address;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.repositories.CenterRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface CenterService {
public Center add(Address address);
}
