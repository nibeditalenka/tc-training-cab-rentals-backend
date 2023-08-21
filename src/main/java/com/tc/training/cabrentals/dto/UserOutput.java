package com.tc.training.cabrentals.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserOutput {
    private UUID id;
    private String name;
    private String email;
    private String password;
}
