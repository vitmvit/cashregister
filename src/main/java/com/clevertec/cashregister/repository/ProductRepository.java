package com.clevertec.cashregister.repository;

import com.clevertec.cashregister.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByDescriptionContainsIgnoreCase(String description);
}
