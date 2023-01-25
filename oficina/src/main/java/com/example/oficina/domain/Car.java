package com.example.oficina.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @NotNull
    private Costumer costumer;
    private String model;
    private String brand;
    private String year;
    @OneToMany
    @JoinTable(
            name = "car_service_order",
            joinColumns = @JoinColumn(name = "OS_id"),
            inverseJoinColumns = @JoinColumn(name = "car")
    )
    private Set<ServiceOrder> serviceOrders;
}
