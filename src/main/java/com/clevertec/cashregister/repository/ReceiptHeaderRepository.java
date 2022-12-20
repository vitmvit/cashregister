package com.clevertec.cashregister.repository;

import com.clevertec.cashregister.entity.ReceiptHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptHeaderRepository extends JpaRepository<ReceiptHeader, Long> {
}
