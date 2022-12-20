package com.clevertec.cashregister.service.impl;

import com.clevertec.cashregister.converter.ProductConverter;
import com.clevertec.cashregister.dto.ProductDto;
import com.clevertec.cashregister.entity.Product;
import com.clevertec.cashregister.repository.ProductRepository;
import com.clevertec.cashregister.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public ProductDto findById(Long id) {
        return productConverter.convert(
                productRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Product not found by barcode: " + id)
                )
        );
    }

    @Override
    public List<ProductDto> findAllByDescription(String description) {
        List<Product> list = productRepository.findAllByDescriptionContainsIgnoreCase(description);
        return list.isEmpty()
                ? Collections.emptyList()
                : list.stream().map(productConverter::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        return productConverter.convert(
                productRepository.save(
                        productConverter.convert(productDto)
                )
        );
    }

    @Override
    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}
