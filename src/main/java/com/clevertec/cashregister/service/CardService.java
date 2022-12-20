package com.clevertec.cashregister.service;

import com.clevertec.cashregister.dto.CardDto;

public interface CardService {

    CardDto findById(Long id);

    CardDto create(String cardType);

    void remove(Long id);
}
