package com.example.ponto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EditLogBody {
    long id;
    LocalDate date;
    LocalTime time;
}
