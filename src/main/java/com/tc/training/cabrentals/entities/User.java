package com.tc.training.cabrentals.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private Long phoneNum;

}
