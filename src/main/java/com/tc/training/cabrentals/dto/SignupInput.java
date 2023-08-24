package com.tc.training.cabrentals.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupInput {
  private String name;
  private String email;
  private String password;
  private String phoneNum;
}
