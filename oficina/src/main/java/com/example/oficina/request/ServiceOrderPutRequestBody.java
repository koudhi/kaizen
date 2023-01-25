package com.example.oficina.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceOrderPutRequestBody {
    private BigDecimal workValue;
    private String description;
}
