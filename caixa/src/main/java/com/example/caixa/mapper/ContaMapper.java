package com.example.caixa.mapper;

import com.example.caixa.domain.Account;
import com.example.caixa.request.PostContaRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    Account toConta(PostContaRequestBody postContaRequestBody);

}
