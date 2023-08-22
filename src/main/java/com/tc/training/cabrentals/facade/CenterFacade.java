package com.tc.training.cabrentals.facade;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.entities.Center;

import java.util.List;
import java.util.UUID;

public interface CenterFacade {
    void add(CenterInput centerInput);

    void delete(UUID id);

    List<Center> getAll();


}
