package com.tc.training.cabrentals.entities;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private Long phoneNum;
    private String firebaseId;

}
