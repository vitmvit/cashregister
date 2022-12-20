package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.dto.ItemDto;
import com.clevertec.cashregister.entity.ReceiptItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiptItemConverter {

    ItemDto convert(ReceiptItem source);
}
