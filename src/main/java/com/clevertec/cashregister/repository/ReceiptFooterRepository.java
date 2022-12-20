package com.clevertec.cashregister.repository;

import com.clevertec.cashregister.entity.ReceiptFooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptFooterRepository extends JpaRepository<ReceiptFooter, Long> {
}
