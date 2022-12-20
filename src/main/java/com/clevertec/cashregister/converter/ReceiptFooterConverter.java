package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.dto.FooterDto;
import com.clevertec.cashregister.entity.ReceiptFooter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiptFooterConverter {

    FooterDto convert(ReceiptFooter source);
}
