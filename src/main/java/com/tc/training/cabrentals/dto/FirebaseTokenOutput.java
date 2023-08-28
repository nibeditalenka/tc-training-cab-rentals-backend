package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class FirebaseTokenOutput {
  public String idToken;
  public String refreshToken;
  public String expiresIn;
  public String localId;
  public Boolean registered;
  public String email;

}
