package com.example.oficina.mapper;

import com.example.oficina.domain.ServiceOrder;
import com.example.oficina.request.ServiceOrderPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface ServiceOrderMapper {
    ServiceOrderMapper INSTANCE = Mappers.getMapper(ServiceOrderMapper.class);
    @Mapping(target = "description", source = "description")
    ServiceOrder toServiceOrder(ServiceOrderPostRequestBody serviceOrderPostRequestBody);
}
