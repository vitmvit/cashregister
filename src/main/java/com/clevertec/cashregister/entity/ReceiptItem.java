package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * на первый взгляд, такая модель вызывает избыточность
 * но зато хранит полное состояние товара на момент продажи
 */
@Getter
@Setter
@Entity
public class ReceiptItem extends BaseEntity {

    @Column(name = "receipt_id", insertable = false, updatable = false)
    private Long receiptId;

    @ManyToOne
    @JoinColumn(
            name = "receipt_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_to_receipt")
    )
    private Receipt receipt;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private Boolean action;

    private Integer quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;
}
