package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.dto.CardDto;
import com.clevertec.cashregister.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardConverter {

    @Mapping(source = "id", target = "number")
    CardDto convert(Card source);

    @Mapping(source = "number", target = "id")
    Card convert(CardDto source);
}
