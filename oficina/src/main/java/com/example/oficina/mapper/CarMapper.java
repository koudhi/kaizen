package com.example.oficina.mapper;

import com.example.oficina.domain.Car;
import com.example.oficina.request.CarPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "model", source = "model")
    Car toCar(CarPostRequestBody carPostRequestBody);
}
