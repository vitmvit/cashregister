package com.clevertec.cashregister.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceiptDto {

    private HeaderDto header;
    private List<ProductDto> items;
    private FooterDto footer;
}
