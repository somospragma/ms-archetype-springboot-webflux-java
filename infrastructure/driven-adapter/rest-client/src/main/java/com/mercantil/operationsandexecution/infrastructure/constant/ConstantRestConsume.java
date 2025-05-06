package com.mercantil.operationsandexecution.infrastructure.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantRestConsume {
    TRANSACTION_ID("transactionId");


    private final String value;
}
