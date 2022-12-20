package com.clevertec.cashregister.converter;

import com.clevertec.cashregister.dto.ProductDto;
import com.clevertec.cashregister.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    ProductDto convert(Product source);

    Product convert(ProductDto source);
}
