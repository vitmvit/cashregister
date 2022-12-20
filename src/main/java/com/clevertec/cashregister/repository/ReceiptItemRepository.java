package com.clevertec.cashregister.repository;

import com.clevertec.cashregister.entity.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptItemRepository extends JpaRepository<ReceiptItem, Long> {

    List<ReceiptItem> findAllByReceiptId(Long receiptId);
}
