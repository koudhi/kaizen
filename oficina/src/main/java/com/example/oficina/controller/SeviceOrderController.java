package com.example.oficina.controller;

import com.example.oficina.domain.ServiceOrder;
import com.example.oficina.reponse.PartsResponse;
import com.example.oficina.request.AddPartBody;
import com.example.oficina.request.ServiceOrderPostRequestBody;
import com.example.oficina.request.ServiceOrderPutRequestBody;
import com.example.oficina.service.ServiceOrderService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/os")
@RequiredArgsConstructor
public class SeviceOrderController {
    private final ServiceOrderService serviceOrderService;

    @GetMapping("/all")
    public ResponseEntity<List<ServiceOrder>> showAll(){
        return new ResponseEntity<>(serviceOrderService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/costumer/{carId}")
    public ResponseEntity<List<ServiceOrder>> showFromCar(@PathVariable long carId){
        return new ResponseEntity<>(serviceOrderService.listFromCar(carId),HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ServiceOrder> newServiceOrder(@Valid @RequestBody ServiceOrderPostRequestBody serviceOrderPostRequestBody){
        return new ResponseEntity<>(serviceOrderService.newServiceOrder(serviceOrderPostRequestBody),HttpStatus.OK);
    }

    @PutMapping("{id}/addPart")
    public ResponseEntity<ServiceOrder> addPart(@PathVariable long id, @RequestBody AddPartBody addPartBody){
        serviceOrderService.addPart(id,addPartBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrder> addPart(@PathVariable long id, @RequestBody ServiceOrderPutRequestBody serviceOrderPutRequestBody){
        serviceOrderService.changeServiceOrder(id,serviceOrderPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceOrder> deleteServiceOrder(@PathVariable long id){
        serviceOrderService.deleteServiceOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<ServiceOrder> closeServiceOrder(@PathVariable long id){
        serviceOrderService.closeServiceOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/reopen")
    public ResponseEntity<ServiceOrder> openServiceOrder(@PathVariable long id){
        serviceOrderService.reopenServiceOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<ServiceOrder> checkout(@PathVariable long id){
        serviceOrderService.checkout(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/listParts")
    public ResponseEntity<List<PartsResponse>> listParts(@PathVariable long id){
        return new ResponseEntity<>(serviceOrderService.listParts(id),HttpStatus.OK);
    }

}
