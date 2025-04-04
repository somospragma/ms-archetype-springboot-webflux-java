package com.mercantil.operationsandexecution.crosscutting.restclient.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantRestConsume {
    TRANSACTION_ID("transactionId");


    private final String value;
}
