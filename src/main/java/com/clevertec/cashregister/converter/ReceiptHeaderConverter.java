package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.converter.uses.DateTimeConverter;
import com.clevertec.cashregister.dto.HeaderDto;
import com.clevertec.cashregister.entity.ReceiptHeader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                DateTimeConverter.class
        }
)
public interface ReceiptHeaderConverter {

    @Mapping(source = "dateTime", target = "date")
    @Mapping(source = "dateTime", target = "time")
    HeaderDto convert(ReceiptHeader source);
}
