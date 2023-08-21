package com.tc.training.cabrentals.entities;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class User extends BaseEntity {
    private String name;
    private String email;
    private String phoneNum;
    private String firebaseId;

}
