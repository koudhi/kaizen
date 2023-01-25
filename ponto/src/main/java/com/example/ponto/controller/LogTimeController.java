package com.example.ponto.controller;

import com.example.ponto.domain.LogTime;
import com.example.ponto.request.EditLogBody;
import com.example.ponto.request.LogTimePostBody;
import com.example.ponto.service.LogTimeService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/log")
@RestController
@RequiredArgsConstructor
public class LogTimeController {
    private final LogTimeService logTimeService;

    @GetMapping("/{id}")
    public ResponseEntity<List<LogTime>> findEmployeLog(@PathVariable long id){
        return new ResponseEntity<>(logTimeService.findEmployeLog(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<LogTime> newLog(@RequestBody LogTimePostBody logTimePostBody){
        return new ResponseEntity<>(logTimeService.newLog(logTimePostBody),HttpStatus.OK);
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<LogTime> newLog(@PathVariable long id){
        return new ResponseEntity<>(logTimeService.newLogById(id),HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<LogTime> editEntry(@RequestBody EditLogBody editLogBody){
        logTimeService.editLog(editLogBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
