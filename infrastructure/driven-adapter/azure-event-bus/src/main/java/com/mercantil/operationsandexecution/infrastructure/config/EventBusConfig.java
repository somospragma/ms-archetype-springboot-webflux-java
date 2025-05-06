package com.mercantil.operationsandexecution.infrastructure.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EventBusConfig {

    @Value("${azure.service-bus.topic-name}")
    private String topicName;

    @Bean
    public ServiceBusSenderAsyncClient serviceBusSenderClient(ServiceBusSecret serviceBusSecret) {
        return new ServiceBusClientBuilder()
                .connectionString(serviceBusSecret.getConnectionString())
                .sender()
                .topicName(topicName)
                .buildAsyncClient();
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
