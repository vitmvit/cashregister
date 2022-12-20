package com.clevertec.cashregister.pattern.fabric.card.impl;

import com.clevertec.cashregister.entity.Card;
import com.clevertec.cashregister.pattern.fabric.card.CardProducer;

public class PensionCard implements CardProducer {

    @Override
    public Card createCard() {
        return new Card(15);
    }
}
