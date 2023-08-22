package com.tc.training.cabrentals.dto;

import com.tc.training.cabrentals.entities.Address;
import lombok.Data;

@Data
public class CenterInput {
   private Address address;
   private String name;
}
