package com.example.oficina.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ServiceOrderPostRequestBody {
    @NotNull
    private long carId;
    private BigDecimal workValue;
    private String description;
    private String status = "open";
    private String usedParts = "";
    private BigDecimal partsValue = new BigDecimal("0.00");
}
