package com.mercantil.operationsandexecution.infrastructure.adapter.keyvaultsecrets;

import com.azure.security.keyvault.secrets.SecretClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercantil.operationsandexecution.infrastructure.adapter.azureeventbus.config.ServiceBusSecret;
import com.mercantil.operationsandexecution.infrastructure.adapter.sqlserver.config.SqlServerConnectionProperties;
import com.mercantil.operationsandexecution.infrastructure.adapter.keyvaultsecrets.model.ConsumerSecrets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
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
                                                   @Value(REST_ENV_NAME) String restSecretName) throws JsonProcessingException {
        /*String stringValue = secretClient.getSecret(restSecretName).getValue();
        return objectMapper.readValue(stringValue, ConsumerSecrets.class);*/
        return new ConsumerSecrets("clientId", "clientSecret");
    }

    @Bean
    @Profile("local")
    public ConsumerSecrets loadConsumerRestSecretsLocal() {
        /*String stringValue = secretClient.getSecret(restSecretName).getValue();
        return objectMapper.readValue(stringValue, ConsumerSecrets.class);*/
        return new ConsumerSecrets("clientId", "clientSecret");
    }

    @Bean
    @Profile("cloud")
    public ServiceBusSecret loadServiceBusSecret(SecretClient secretClient, @Value("${azure.service-bus.connection-string}") String connectionString) {
        return new ServiceBusSecret(secretClient.getSecret(connectionString).getValue());
    }

    @Bean
    @Profile("local")
    public ServiceBusSecret loadServiceBusSecretLocal() {
        return new ServiceBusSecret("Endpoint=sb://mercantil.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=1234");
    }

}
