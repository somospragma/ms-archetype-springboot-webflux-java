package com.mercantil.operationsandexecution.crosscutting.events.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
