package com.clevertec.cashregister.service;

import com.clevertec.cashregister.constants.CardType;
import com.clevertec.cashregister.dto.CardDto;
import com.clevertec.cashregister.service.impl.CardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardServiceImpl cardServiceImpl;

    private CardDto cardDto;

    @BeforeEach
    public void setup() {
        cardService = cardServiceImpl;
        CardDto card = new CardDto();
        card.setNumber(1L);
        card.setPercent(10);
        cardDto = card;
    }

    @Test
    public void findByIdTest() {
        CardDto savedCardDto = cardService.create(CardType.S.getType());
        Assertions.assertEquals(1L, savedCardDto.getNumber());
        Assertions.assertEquals(10, savedCardDto.getPercent());
    }

    @Test
    public void createTest() {
        CardDto savedCardDto = cardService.create(CardType.S.getType());
        Assertions.assertNotNull(savedCardDto);
    }
}
