package com.example.ponto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmployePutBody {
    @NotNull(message = "")
    private Long Id;
    @NotEmpty(message = "")
    private String name;
    @NotEmpty(message = "")
    private String function;
    @NotNull(message = "")
    @Column(unique = true)
    private Long code;
}