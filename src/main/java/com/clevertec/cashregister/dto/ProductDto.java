package com.clevertec.cashregister.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class ProductDto {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    private boolean action;
}
