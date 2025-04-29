package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.secrets;

import com.azure.security.keyvault.secrets.SecretClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretService implements ISecretService{

    private final SecretClient secretClient;

    @Override
    public String getSecret(String secretName) {
        return secretClient.getSecret(secretName).getValue();
    }
}
