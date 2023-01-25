package com.example.oficina.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarPartPostRequestBody {
    private String name;
    private BigDecimal value;
    private String description;
    private double stock;
}
