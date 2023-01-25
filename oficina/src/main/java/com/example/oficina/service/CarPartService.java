package com.example.oficina.service;

import com.example.oficina.domain.CarPart;
import com.example.oficina.mapper.CarPartMapper;
import com.example.oficina.repository.CarPartsRepository;
import com.example.oficina.request.CarPartPostRequestBody;
import com.example.oficina.request.ChangeCarPartStockBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarPartService {
    private final CarPartsRepository carPartsRepository;

    public List<CarPart> listAll(){
        return carPartsRepository.findAll();
    }

    public CarPart findById(long id){
        return carPartsRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car part not found."));
    }

    public void reduceStock(ChangeCarPartStockBody changeCarPartStockBody) {
        CarPart carPart = findById(changeCarPartStockBody.getPartId());
        carPart.setStock(
                carPart.getStock()-changeCarPartStockBody.getQuantity()
        );
        carPartsRepository.save(carPart);
    }

    public void increaseStock(ChangeCarPartStockBody changeCarPartStockBody) {
        CarPart carPart = findById(changeCarPartStockBody.getPartId());
        carPart.setStock(
                carPart.getStock()+changeCarPartStockBody.getQuantity()
        );
        carPartsRepository.save(carPart);
    }

    public CarPart newPart(CarPartPostRequestBody carPartPostRequestBody) {
        System.out.println(carPartPostRequestBody);
        CarPart carPart = CarPartMapper.INSTANCE.postToCarPart(carPartPostRequestBody);
        System.out.println(carPart);
        return carPartsRepository.save(carPart);
    }

}
