package com.clevertec.cashregister.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class HeaderDto {

    private String title;
    private String store;
    private String address;
    private String phone;
    private LocalDate date;
    private LocalTime time;
}
