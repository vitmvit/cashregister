package com.clevertec.cashregister.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ErrorField {

    private String object;
    private String field;
    private Object value;
    private String message;
}