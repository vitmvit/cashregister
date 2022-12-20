package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.dto.ReceiptDto;
import com.clevertec.cashregister.entity.Receipt;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                ReceiptHeaderConverter.class,
                ProductConverter.class,
                ReceiptFooterConverter.class
        }
)
public interface ReceiptConverter {

    ReceiptDto convert(Receipt source);
}
