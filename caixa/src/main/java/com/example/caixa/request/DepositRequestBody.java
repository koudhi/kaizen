package com.example.caixa.request;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DepositRequestBody {
    @NotNull(message = "account number must be informed")
    private Long accountNumber;
    @DecimalMin(value = "0.0",inclusive = false, message = "vlue must be positive")
    private BigDecimal value;
}
