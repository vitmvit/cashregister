package com.clevertec.cashregister.service.impl;

import com.clevertec.cashregister.converter.CardConverter;
import com.clevertec.cashregister.dto.CardDto;
import com.clevertec.cashregister.pattern.fabric.CardFabric;
import com.clevertec.cashregister.repository.CardRepository;
import com.clevertec.cashregister.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    @Override
    public CardDto findById(Long id) {
        return cardConverter.convert(
                cardRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Discount card not found by number: " + id)
                )
        );
    }

    @Override
    @Transactional
    public CardDto create(String cardType) {
        return cardConverter.convert(
                cardRepository.save(
                        new CardFabric().create(cardType)
                )
        );
    }

    @Override
    @Transactional
    public void remove(Long id) {
        cardRepository.deleteById(id);
    }
}
