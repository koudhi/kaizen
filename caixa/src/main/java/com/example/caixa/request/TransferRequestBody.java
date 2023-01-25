package com.example.caixa.request;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequestBody {
    @NotNull(message = "account number must be informed")
    private long accountNumber;
    @DecimalMin(value = "0.0",inclusive = false, message = "vlue must be positive")
    private BigDecimal value;
    @NotNull(message = "destinatary account number must be informed")
    private long destinataryAccountNumber;
}
