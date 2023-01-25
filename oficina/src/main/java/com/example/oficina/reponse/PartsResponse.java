package com.example.oficina.reponse;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PartsResponse {
    private Long partId;
    private String partName;
    private String partDescription;
    private BigDecimal unitValue;
    private Long quantity;
    private BigDecimal partTotal;

}
