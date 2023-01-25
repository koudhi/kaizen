package com.example.ponto.util;

import com.example.ponto.domain.Employe;

public class EmployeCreator {

    public static Employe createEmploye(){
        return Employe.builder()
                .name("Teste")
                .alerts(0)
                .function("teste")
                .code(1234L)
                .build();
    }

}
