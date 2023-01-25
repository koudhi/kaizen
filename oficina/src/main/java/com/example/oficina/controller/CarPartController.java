package com.example.oficina.controller;

import com.example.oficina.domain.CarPart;
import com.example.oficina.request.CarPartPostRequestBody;
import com.example.oficina.request.ChangeCarPartStockBody;
import com.example.oficina.service.CarPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
@RequiredArgsConstructor
public class CarPartController {
    private final CarPartService carPartService;

    @GetMapping("/all")
    public ResponseEntity<List<CarPart>> listAll(){
        return new ResponseEntity<>(carPartService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarPart> findById(@PathVariable long id){
        return new ResponseEntity<>(carPartService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarPart> newPart(@RequestBody CarPartPostRequestBody carPartPostRequestBody){
        return new ResponseEntity<>(carPartService.newPart(carPartPostRequestBody),HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity<CarPart> usePart(@RequestBody ChangeCarPartStockBody changeCarPartStockBody){
        carPartService.reduceStock(changeCarPartStockBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/add")
    public ResponseEntity<CarPart> increaseStock(@RequestBody ChangeCarPartStockBody changeCarPartStockBody){
        carPartService.increaseStock(changeCarPartStockBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
