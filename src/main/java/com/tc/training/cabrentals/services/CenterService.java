package com.tc.training.cabrentals.services;

import com.tc.training.cabrentals.entities.Center;

import java.util.List;
import java.util.UUID;


public interface CenterService {
    public Center add(Center center);

    public Center centerById(UUID id);

    public void deleteCenter(Center center);

    void deleteById(UUID id);

    List<Center> getAll();

}
