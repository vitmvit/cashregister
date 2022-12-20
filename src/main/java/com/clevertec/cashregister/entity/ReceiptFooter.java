package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ReceiptFooter extends BaseEntity {

    @Column(precision = 10, scale = 2)
    private BigDecimal taxTotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;
}
