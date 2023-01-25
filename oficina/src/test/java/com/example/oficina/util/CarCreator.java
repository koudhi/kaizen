package com.example.oficina.util;

import com.example.oficina.domain.Car;

public class CarCreator {

    public static Car baseCar(){
        return Car.builder()
                .id(1)
                .brand("Corsa")
                .model("Chevrolet")
                .contactNumber("159357")
                .year("20/20")
                .costumer("São João")
                .build();
    }

}
