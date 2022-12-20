package com.clevertec.cashregister.constants;

import lombok.Getter;

public enum CardType {

    C("COMMON"),
    S("SOCIAL"),
    P("PENSION");

    @Getter
    private final String type;

    CardType(String type) {
        this.type = type;
    }
}
