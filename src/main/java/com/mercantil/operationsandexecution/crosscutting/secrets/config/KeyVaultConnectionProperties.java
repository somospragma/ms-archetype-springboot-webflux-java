package com.mercantil.operationsandexecution.crosscutting.secrets.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "azure.keyvault.properties")
public class KeyVaultConnectionProperties {
    private String uri;
    private String clientId;
    private String clientSecret;
    private String tenantId;
}
