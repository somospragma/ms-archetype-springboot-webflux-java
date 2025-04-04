package com.mercantil.operationsandexecution.crosscutting.secrets.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeyVaultConnectionProperties.class)
public class KeyVaultConfig {

    //Uncomment when has real vault instance.
    @Bean
    public SecretClient secretClient(KeyVaultConnectionProperties properties){
        ClientSecretCredential secretCredential = new ClientSecretCredentialBuilder()
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .tenantId(properties.getTenantId())
                .build();

        return new SecretClientBuilder()
                .vaultUrl(properties.getUri())
                .credential(secretCredential)
                .buildClient();
    }
}
