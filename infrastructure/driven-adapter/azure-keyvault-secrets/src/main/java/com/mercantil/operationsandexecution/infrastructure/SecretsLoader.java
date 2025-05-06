package com.mercantil.operationsandexecution.infrastructure;

import com.azure.security.keyvault.secrets.SecretClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.mercantil.operationsandexecution.infrastructure.model.ConsumerSecrets;
import com.mercantil.operationsandexecution.infrastructure.model.SqlServerConnectionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class SecretsLoader {

    private static final String SQL_ENV_NAME = "${azure.keyvault.secrets.sqlsecretsname}";
    private static final String REST_ENV_NAME = "${azure.keyvault.secrets.consumersecret}";

    @Bean
    @Profile("cloud")
    public SqlServerConnectionProperties loadSqlServerSecretsCloud(SecretClient secretClient,
                                                                   @Value(SQL_ENV_NAME) String sqlSecretName,
                                                                   ObjectMapper objectMapper) throws JsonProcessingException {
        String stringValue = secretClient.getSecret(sqlSecretName).getValue();
        return objectMapper.readValue(stringValue, SqlServerConnectionProperties.class);
    }

    @Bean
    @Profile("local")
    public SqlServerConnectionProperties loadSqlServerSecrets(){
        return SqlServerConnectionProperties.builder()
                .host("localhost")
                .port(1433)
                .dbname("mercantil")
                .username("admin")
                .password("Admin123456")
                .build();
    }

    @Bean
    @Profile("cloud")
    public ConsumerSecrets loadConsumerRestSecrets(SecretClient secretClient, ObjectMapper objectMapper,
                                                   @Value(REST_ENV_NAME) String restSecretName)
            throws JsonProcessingException {
        String stringValue = secretClient.getSecret(restSecretName).getValue();
        return objectMapper.readValue(stringValue, ConsumerSecrets.class);
    }

    @Bean
    @Profile("local")
    public ConsumerSecrets loadConsumerRestSecretsLocal(@Value("${azure.secrets.secret-name}") String restSecretName) {
        return new ConsumerSecrets("clientId", "clientSecret");
    }

}
