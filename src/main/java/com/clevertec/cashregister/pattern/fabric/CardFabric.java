package com.clevertec.cashregister.pattern.fabric;

import com.clevertec.cashregister.constants.CardType;
import com.clevertec.cashregister.entity.Card;
import com.clevertec.cashregister.exception.UnknownCardType;
import com.clevertec.cashregister.pattern.fabric.card.CardProducer;
import com.clevertec.cashregister.pattern.fabric.card.impl.CommonCard;
import com.clevertec.cashregister.pattern.fabric.card.impl.PensionCard;
import com.clevertec.cashregister.pattern.fabric.card.impl.SocialCard;

public class CardFabric {

    public Card create(String cardType) {
        CardProducer cardProducer = null;
        if (CardType.C.name().equalsIgnoreCase(cardType)) {
            cardProducer = new CommonCard();
        } else if (CardType.S.name().equalsIgnoreCase(cardType)) {
            cardProducer = new SocialCard();
        } else if (CardType.P.name().equalsIgnoreCase(cardType)) {
            cardProducer = new PensionCard();
        }
        if (cardProducer == null) {
            throw new UnknownCardType();
        } else {
            return cardProducer.createCard();
        }
    }
}
