package com.example.oficina.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPostRequestBody {
    @NotNull
    private long costumerId;
    private String model;
    private String brand;
    private String year;
}
