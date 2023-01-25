package com.example.ponto.service;

import com.example.ponto.domain.Employe;
import com.example.ponto.domain.LogTime;
import com.example.ponto.repository.LogTimeRepository;
import com.example.ponto.request.EditLogBody;
import com.example.ponto.request.LogTimePostBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogTimeService {
    private final LogTimeRepository logTimeRepository;
    private final EmployeService employeService;


    public List<LogTime> findEmployeLog(long id) {
        Employe employe = employeService.findById(id);
        return logTimeRepository.findByEmploye(employe);
    }

    public LogTime newLog(LogTimePostBody logTimePostBody) {

        Employe employe = employeService.findByCode(logTimePostBody.getCode());
        if (employe.getBlocked()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuario blockeado! Se apresente ao RH!!");
        }else {
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();
            LogTime logTime = LogTime.builder()
                    .date(date)
                    .time(time)
                    .type(employe.getNextLog())
                    .build();
            employeService.setNextLog(employe,time);
            logTime.setEmploye(employe);
            return logTimeRepository.save(logTime);
        }
    }

    public LogTime newLogById(long id) {

        Employe employe = employeService.findById(id);
        if (employe.getBlocked()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuario blockeado! Se apresente ao RH!!");
        }else {
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();
            LogTime logTime = LogTime.builder()
                    .date(date)
                    .time(time)
                    .type(employe.getNextLog())
                    .build();
            employeService.setNextLog(employe,time);
            logTime.setEmploye(employe);
            return logTimeRepository.save(logTime);
        }
    }

    public LogTime findById(long id){
        return logTimeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Entry not found!"));
    }

    public void editLog(EditLogBody editLogBody) {
        LogTime logTime = findById(editLogBody.getId());
        logTime.setTime(editLogBody.getTime());
        logTime.setDate(editLogBody.getDate());
        logTimeRepository.save(logTime);

    }

}
