package com.example.oficina.controller;

import com.example.oficina.domain.Costumer;
import com.example.oficina.request.NewCostumerBody;
import com.example.oficina.service.CostumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumer")
@RequiredArgsConstructor
public class CostumerController {
    private final CostumerService costumerService;

    @GetMapping
    public ResponseEntity<List<Costumer>> listAll(){
        return ResponseEntity.ok(costumerService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Costumer> listById(@PathVariable Long id){
        return ResponseEntity.ok(costumerService.listById(id));
    }
    @PostMapping("/new")
    public ResponseEntity<Costumer> listAll(@RequestBody NewCostumerBody newCostumerBody){
        return ResponseEntity.ok(costumerService.newCostumer(newCostumerBody));
    }


}
