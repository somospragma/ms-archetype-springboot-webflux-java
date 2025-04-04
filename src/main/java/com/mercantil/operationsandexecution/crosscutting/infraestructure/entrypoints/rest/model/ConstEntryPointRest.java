package com.mercantil.operationsandexecution.crosscutting.infraestructure.entrypoints.rest.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstEntryPointRest {

    @UtilityClass
    public static class HeaderKeys {
        public static final String MESSAGE_ID = "message-id";
        public static final String TRANSACTION_ID = "transactionId";
    }
}
