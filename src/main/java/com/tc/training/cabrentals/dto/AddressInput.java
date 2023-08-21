package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class AddressInput {
    private  String addressLine1;
    private String addressLine2;
    private String landMark;
    private Integer pinCode;
    private String city;
}
