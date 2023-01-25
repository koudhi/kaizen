package com.example.oficina.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
public class ChangeCarPartStockBody {
    @NotNull
    private long partId;
    @PositiveOrZero
    private long quantity;
}
