package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.secrets;

import com.azure.security.keyvault.secrets.SecretClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.dataproviders.database.sqlserver.config.SqlServerConnectionProperties;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.dataproviders.restclient.model.ConsumerSecrets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretsLoader {

    private static final String SQL_ENV_NAME = "${azure.keyvault.secrets.sqlsecretsname}";
    private static final String REST_ENV_NAME = "${azure.keyvault.secrets.consumersecret}";

    //@Bean
    public SqlServerConnectionProperties loadSqlServerSecrets(SecretClient secretClient,
                                                              @Value(SQL_ENV_NAME) String sqlSecretName,
                                                              ObjectMapper objectMapper) throws JsonProcessingException {
        String stringValue = secretClient.getSecret(sqlSecretName).getValue();
        return objectMapper.readValue(stringValue, SqlServerConnectionProperties.class);
    }

    // TODO: 24/01/25 Delete when secret is consumed.
    @Bean
    public SqlServerConnectionProperties loadSqlServerSecrets(){
        return SqlServerConnectionProperties.builder()
                .host("localhost")
                .port(1433)
                .dbname("mercantil")
                .username("SA")
                .password("reallyStrongPwd123")
                .build();
    }

    @Bean
    public ConsumerSecrets loadConsumerRestSecrets(SecretClient secretClient, ObjectMapper objectMapper,
                                                   @Value(REST_ENV_NAME) String restSecretName) throws JsonProcessingException {
        /*String stringValue = secretClient.getSecret(restSecretName).getValue();
        return objectMapper.readValue(stringValue, ConsumerSecrets.class);*/
        return new ConsumerSecrets("clientId", "clientSecret");
    }
}
