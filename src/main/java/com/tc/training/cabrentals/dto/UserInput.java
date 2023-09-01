package com.tc.training.cabrentals.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
  private String name;
  private String email;
  private String phoneNumber;
  private UUID centerId;
  private String password;
}
