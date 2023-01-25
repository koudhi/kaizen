package com.example.oficina.service;

import com.example.oficina.domain.Car;
import com.example.oficina.domain.CarPart;
import com.example.oficina.domain.ServiceOrder;
import com.example.oficina.mapper.ServiceOrderMapper;
import com.example.oficina.reponse.PartsResponse;
import com.example.oficina.repository.ServiceOrderRepository;
import com.example.oficina.request.AddPartBody;
import com.example.oficina.request.ChangeCarPartStockBody;
import com.example.oficina.request.ServiceOrderPostRequestBody;
import com.example.oficina.request.ServiceOrderPutRequestBody;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {
    private final ServiceOrderRepository serviceOrderRepository;
    private final CarService carService;
    private final CarPartService carPartService;

    public List<ServiceOrder> listAll() {
        return serviceOrderRepository.findAll();
    }

    public ServiceOrder findById(long id){
        return serviceOrderRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Service order not found"));
    }

    public List<ServiceOrder> listFromCar(long carId) {
        Car car = carService.listById(carId);
        return serviceOrderRepository.findByCar(car);
    }

    public ServiceOrder newServiceOrder(ServiceOrderPostRequestBody serviceOrderPostRequestBody) {
        ServiceOrder serviceOrder = ServiceOrderMapper.INSTANCE.toServiceOrder(serviceOrderPostRequestBody);
        Car car = carService.listById(serviceOrderPostRequestBody.getCarId());
        checkForOpenServiceOrder(car);
        serviceOrder.setCar(car);
        return serviceOrderRepository.save(serviceOrder);
    }

    private void checkForOpenServiceOrder(Car car) {
        listFromCar(car.getId()).forEach(order -> {
            if (order.getStatus().equals("open")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This car has an open OS");
            }
        });
    }

    public void addPart(long id, AddPartBody addPartBody) {
        ServiceOrder serviceOrder = findById(id);
        checkServiceStatus(serviceOrder,"closed");
        String usedParts = serviceOrder.getUsedParts().concat(" ,"+
                addPartBody.getParts().toString().replaceAll("[{}]","")
        );
        serviceOrder.setUsedParts(usedParts);
        serviceOrderRepository.save(serviceOrder);
        updateParts(id);
    }

    public void updateParts(long id){
        ServiceOrder serviceOrder = findById(id);
        HashMap<Long,Long> usedParts = new HashMap<Long,Long>();
        stringToPartIdAndUnit(serviceOrder.getUsedParts(),(partId,partUnit)->
            usedParts.put(partId, partUnit+ (usedParts.containsKey(partId) ? usedParts.get(partId) : 0)));
        serviceOrder.setUsedParts(usedParts.toString().replaceAll("[{}]",""));
        serviceOrder.setPartsValue(checkPartValue(usedParts));
        serviceOrderRepository.save(serviceOrder);
    }

    public BigDecimal checkPartValue(HashMap<Long,Long> usedParts){
        AtomicReference<BigDecimal> partsValue = new AtomicReference<>();
        partsValue.set(BigDecimal.ZERO);
        usedParts.forEach((partId,unit)->{
            CarPart carPart = carPartService.findById(partId);
            BigDecimal partValue = carPart.getValue();
            BigDecimal unitBD = new BigDecimal(unit);
            partsValue.set(partsValue.get().add(partValue.multiply(unitBD)));
        });
        return partsValue.get();
    }

    public void changeServiceOrder(long id, ServiceOrderPutRequestBody serviceOrderPutRequestBody){
        ServiceOrder serviceOrder = findById(id);
        if(serviceOrderPutRequestBody.getDescription()!=null) {
            serviceOrder.setDescription(serviceOrderPutRequestBody.getDescription());
        }

        serviceOrder.setWorkValue(serviceOrderPutRequestBody.getWorkValue());
        serviceOrderRepository.save(serviceOrder);

    }

    public void closeServiceOrder(long id){
        ServiceOrder serviceOrder = findById(id);
        checkServiceStatus(serviceOrder,"closed");
        serviceOrder.setStatus("closed");
        serviceOrderRepository.save(serviceOrder);
    }

    public void reopenServiceOrder(long id){
        ServiceOrder serviceOrder = findById(id);
        checkServiceStatus(serviceOrder,"open");
        checkForOpenServiceOrder(serviceOrder.getCar());
        serviceOrder.setStatus("open");
        serviceOrderRepository.save(serviceOrder);
    }

    public void deleteServiceOrder(long id) {
        ServiceOrder serviceOrder = findById(id);
        serviceOrderRepository.delete(serviceOrder);
    }

    public void checkout(long id){
        ServiceOrder serviceOrder = findById(id);

        closeServiceOrder(id);
        serviceOrder.setBilled(Boolean.TRUE);
        serviceOrderRepository.save(serviceOrder);
        stringToPartIdAndUnit(serviceOrder.getUsedParts(),(partId,partUnit)->{
            ChangeCarPartStockBody changeCarPartStockBody = ChangeCarPartStockBody.builder()
                    .partId(partId).quantity(partUnit).build();
            carPartService.reduceStock(changeCarPartStockBody);
        });
    }

    public List<PartsResponse> listParts(long id) {
        List<PartsResponse> partList = new ArrayList<>();
        ServiceOrder serviceOrder = findById(id);
        for (String i:serviceOrder.getUsedParts().split(",")){
            String[] partInformation = i.split("=");
            Long partId = Long.parseLong(partInformation[0].trim());
            Long partUnit = Long.parseLong(partInformation[1].trim());
            CarPart carPart = carPartService.findById(partId);
            BigDecimal total = carPart.getValue().multiply(new BigDecimal(partUnit));
            PartsResponse newPart = PartsResponse.builder()
                    .partId(partId)
                    .partDescription(carPart.getDescription())
                    .partName(carPart.getName())
                    .unitValue(carPart.getValue())
                    .quantity(partUnit)
                    .partTotal(total)
                    .build();
            partList.add(newPart);
        }
        System.out.println(partList);
        return partList;
    }

    private void stringToPartIdAndUnit(String usedPartsStr,BiConsumer<Long,Long> func){
        for (String i:usedPartsStr.split(",")){
            if(i.trim().equals("")){
                continue;
            }
            String[] partInformation = i.split("=");
            Long partId = Long.parseLong(partInformation[0].trim());
            Long partUnit = Long.parseLong(partInformation[1].trim());
            func.accept(partId,partUnit);
        }
    }

    private void checkServiceStatus(ServiceOrder serviceOrder, String status) {
        if (serviceOrder.getStatus().equals(status)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The OS is "+status);
        }
    }
}
