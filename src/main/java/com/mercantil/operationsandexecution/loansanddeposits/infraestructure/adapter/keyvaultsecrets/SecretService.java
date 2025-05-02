package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.keyvaultsecrets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretService implements ISecretService{

    //private final SecretClient secretClient;

    @Override
    public String getSecret(String secretName) {
        //return secretClient.getSecret(secretName).getValue();
        return "";
    }

}
