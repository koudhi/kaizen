package com.example.oficina.mapper;

import com.example.oficina.domain.Costumer;
import com.example.oficina.request.NewCostumerBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CostumerMapper {
    CostumerMapper INSTANCE = Mappers.getMapper(CostumerMapper.class);

    @Mapping(source = "name", target = "name")
    Costumer newToCostumer(NewCostumerBody newCostumerBody);
}
