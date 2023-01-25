package com.example.oficina.mapper;

import com.example.oficina.domain.CarPart;
import com.example.oficina.request.CarPartPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarPartMapper {
    CarPartMapper INSTANCE = Mappers.getMapper(CarPartMapper.class);
    @Mapping(source = "name", target = "name")
    CarPart postToCarPart(CarPartPostRequestBody carPartPostRequestBody);
}
