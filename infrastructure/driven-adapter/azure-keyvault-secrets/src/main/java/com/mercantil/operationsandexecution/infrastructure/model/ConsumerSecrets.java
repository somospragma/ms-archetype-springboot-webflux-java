package com.mercantil.operationsandexecution.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsumerSecrets {
    private String clientId;
    private String clientSecret;
}
