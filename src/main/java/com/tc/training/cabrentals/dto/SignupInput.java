package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class SignupInput {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;



}
