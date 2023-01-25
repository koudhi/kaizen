package com.example.oficina.controller;


import com.example.oficina.domain.Car;
import com.example.oficina.request.CarPostRequestBody;
import com.example.oficina.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listAll(){
        return new ResponseEntity<>(carService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> listById(@PathVariable long id){
        return new ResponseEntity<>(carService.listById(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Car> newCar(@Valid @RequestBody CarPostRequestBody carPostRequestBody ){
        return new ResponseEntity<>(carService.newCar(carPostRequestBody),HttpStatus.OK);
    }

}
