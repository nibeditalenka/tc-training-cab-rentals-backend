package com.tc.training.cabrentals.dto;

import java.util.UUID;

import com.tc.training.cabrentals.entities.Address;

import lombok.Data;

@Data
public class CenterOutput {
  private UUID id;
  private Address address;
  private String name;
  private Boolean isActive = Boolean.TRUE;
}
