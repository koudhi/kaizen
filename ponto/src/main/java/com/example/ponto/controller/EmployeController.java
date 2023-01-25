package com.example.ponto.controller;

import com.example.ponto.domain.Employe;
import com.example.ponto.request.EmployePostBody;
import com.example.ponto.request.EmployePutBody;
import com.example.ponto.service.EmployeService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/employe")
@RestController
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;

    @GetMapping
    public ResponseEntity<List<Employe>> findAll(){
        return new ResponseEntity<>(employeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Employe> findById(@PathVariable long id){
        return new ResponseEntity<>(employeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    private ResponseEntity<List<Employe>> findByName(@RequestParam(required = false) String name){
        return new ResponseEntity<>(employeService.findByName(name), HttpStatus.OK);
    }


    @PostMapping
    private ResponseEntity<Employe> newEmploye(@Valid @RequestBody EmployePostBody employePostBody){
        return new ResponseEntity<>(employeService.newEmploye(employePostBody), HttpStatus.OK);
    }

    @PutMapping("/modify")
    private ResponseEntity<Employe> editEmploye(@Valid @RequestBody EmployePutBody employePutBody){
        employeService.editEmploye(employePutBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/liberate/{id}")
    private ResponseEntity<Employe> liberate(@PathVariable long id){
        employeService.liberate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reset/{id}")
    private ResponseEntity<Employe> resetAlert(@PathVariable long id){
        employeService.resetAlert(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/remove/{id}")
    private ResponseEntity<Employe> removeEmploye( @PathVariable long id){
        employeService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
