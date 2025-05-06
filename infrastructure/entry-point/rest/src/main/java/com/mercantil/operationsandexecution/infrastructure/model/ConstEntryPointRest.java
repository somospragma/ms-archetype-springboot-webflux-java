package com.mercantil.operationsandexecution.infrastructure.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstEntryPointRest {

    @UtilityClass
    public static class HeaderKeys {
        public static final String MESSAGE_ID = "message-id";
        public static final String TRANSACTION_ID = "transactionId";
        public static final String CLIENT_ID = "clientId";
        public static final String CLIENT_SECRET = "clientSecret";
    }
}
