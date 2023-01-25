package com.example.ponto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmployePostBody {
    @NotEmpty(message = "")
    private String employeName;
    @NotEmpty(message = "")
    private String function;
    @NotNull(message = "")
    private Long code;
}
