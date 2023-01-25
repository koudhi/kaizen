package com.example.oficina.request;

import lombok.Data;

import java.util.HashMap;

@Data
public class AddPartBody {
    private HashMap<Long,Long> parts;
}
