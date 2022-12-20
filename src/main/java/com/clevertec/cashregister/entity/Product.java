package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Product extends BaseEntity {

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private Boolean action;
}
