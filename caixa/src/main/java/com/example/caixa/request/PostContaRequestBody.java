package com.example.caixa.request;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class PostContaRequestBody {
    @NotEmpty(message = "titular can't be empty")
    private String titular;
    @DecimalMin(value = "0.0",inclusive = false, message = "vlue must be positive")
    private BigDecimal saldo;

}
