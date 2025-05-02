package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.keyvaultsecrets.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsumerSecrets {
    private String clientId;
    private String clientSecret;
}
