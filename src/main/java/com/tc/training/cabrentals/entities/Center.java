package com.tc.training.cabrentals.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "centers")
public class Center extends BaseEntity{
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
