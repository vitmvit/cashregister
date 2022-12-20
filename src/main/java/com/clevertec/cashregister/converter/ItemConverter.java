package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.entity.Product;
import com.clevertec.cashregister.entity.ReceiptItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemConverter {

    ReceiptItem convert(Product source);
}
