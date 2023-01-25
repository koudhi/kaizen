package com.example.ponto.service;

import com.example.ponto.domain.Employe;
import com.example.ponto.domain.LogTime;
import com.example.ponto.mapper.EmployeMapper;
import com.example.ponto.repository.EmployeRepository;
import com.example.ponto.repository.LogTimeRepository;
import com.example.ponto.request.EmployePostBody;
import com.example.ponto.request.EmployePutBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeService {
    private final EmployeRepository employeRepository;
    private final LogTimeRepository logTimeRepository;


    public List<Employe> findAll() {
        return employeRepository.findAll();
    }


    public Employe findById(long id) {
        return employeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employe not found"));
    }

    public Employe findByCode(long code) {
        return employeRepository.findByCode(code)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employe not found"));
    }

    public List<Employe> findByName(String name) {
        return employeRepository.findByName(name);
    }

    public Employe newEmploye(EmployePostBody employePostBody) {
        Employe employe= EmployeMapper.INSTANCE.toEmploye(employePostBody);
//        if(employeRepository.findByCode(employe.getCode()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"code must be unique");
//        }


        return employeRepository.save(employe);
    }

    public void editEmploye(EmployePutBody employePutBody) {
        long id = employePutBody.getId();
        Boolean blocked = findById(id).getBlocked();
        Integer alerts = findById(id).getAlerts();
        Employe editedEmploye = EmployeMapper.INSTANCE.toEmploye(employePutBody);
        editedEmploye.setBlocked(blocked);
        editedEmploye.setAlerts(alerts);
        employeRepository.save(editedEmploye);
    }

    public void liberate(long id) {
        Employe employe = findById(id);
        if(employe.getBlocked()){
            employe.setBlocked(false);
            employe.setAlerts(0);
            employeRepository.save(employe);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Funcionario não bloqueado");
        }
    }

    public Employe increaseAlert(Employe employe){
        employe.setAlerts(employe.getAlerts()+1);
        if (employe.getAlerts()==3){
            employe.setBlocked(true);
        }
        return employe;
    }
    public void setNextLog(Employe employe, LocalTime localTime) {

        switch(employe.getNextLog()) {
            case "1st period - entrance":
                if (localTime.isAfter(LocalTime.of(8,0))){
                    increaseAlert(employe);
                }
                employe.setNextLog("1st period - exit");
                break;
            case "1st period - exit":
                employe.setNextLog("2nd period - entrance");
                break;
            case "2nd period - entrance":
                employe.setNextLog("2nd period - exit");
                if (localTime.isAfter(LocalTime.of(14,0))){
                    increaseAlert(employe);
                }
                break;
            case "2nd period - exit":
                employe.setNextLog("1st period - entrance");
                break;
        }

        employeRepository.save(employe);
    }

    public void resetAlert(long id) {
        Employe employe = findById(id);
        employe.setAlerts(0);
        employeRepository.save(employe);
    }

    public void remove(long id) {
        Employe employe = findById(id);
        List<LogTime> logList = logTimeRepository.findByEmploye(employe);
        logList.forEach(log ->logTimeRepository.delete(log));

        employeRepository.delete(employe);
    }
}
