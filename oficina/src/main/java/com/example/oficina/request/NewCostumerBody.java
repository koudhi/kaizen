package com.example.oficina.request;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class NewCostumerBody {
    @NotNull
    private String name;
    private String contactNumber;
    private String eMail;
}
