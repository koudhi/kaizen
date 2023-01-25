package com.example.oficina.service;

import com.example.oficina.domain.Costumer;
import com.example.oficina.mapper.CostumerMapper;
import com.example.oficina.repository.CostumerRepository;
import com.example.oficina.request.NewCostumerBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CostumerService {
    private final CostumerRepository costumerRepository;


    public List<Costumer> listAll() {
        return costumerRepository.findAll();
    }

    public Costumer listById(Long id) {
        return costumerRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Costumer not found!")
        );
    }

    public Costumer newCostumer(NewCostumerBody newCostumerBody) {
        Costumer costumer = CostumerMapper.INSTANCE.newToCostumer(newCostumerBody);
        return costumerRepository.save(costumer);
    }
}
