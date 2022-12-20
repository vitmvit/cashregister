package com.clevertec.cashregister.service;

import com.clevertec.cashregister.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> findAllByDescription(String description);

    ProductDto save(ProductDto productDto);

    void remove(Long id);
}
