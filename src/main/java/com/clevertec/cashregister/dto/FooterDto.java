package com.clevertec.cashregister.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FooterDto {

    private BigDecimal taxTotal;
    private BigDecimal discount;
    private BigDecimal total;
}
