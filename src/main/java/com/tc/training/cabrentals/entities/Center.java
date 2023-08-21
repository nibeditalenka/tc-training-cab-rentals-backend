package com.tc.training.cabrentals.entities;

import jakarta.persistence.OneToOne;

public class Center extends BaseEntity{
    @OneToOne
    private Address address;
}
