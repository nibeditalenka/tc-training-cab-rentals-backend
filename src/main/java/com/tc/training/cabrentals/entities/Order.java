package com.tc.training.cabrentals.entities;

import com.tc.training.cabrentals.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order extends BaseEntity {
    private LocalDateTime pickUpDate;
    private LocalDateTime returnDate;
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "order")
    private List<Car> car;
}
