package com.example.ponto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class LogTimePostBody {
    private long code;
    private LocalDate date;
    private LocalTime time;

}
