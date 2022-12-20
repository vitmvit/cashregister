package com.clevertec.cashregister.exception;

public class UnknownCardType extends RuntimeException {

    public UnknownCardType() {
        this("Unknown card type");
    }

    public UnknownCardType(String message) {
        super(message);
    }
}
