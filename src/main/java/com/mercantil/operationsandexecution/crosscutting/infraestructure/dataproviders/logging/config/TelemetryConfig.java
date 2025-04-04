package com.mercantil.operationsandexecution.crosscutting.infraestructure.dataproviders.logging.config;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.TelemetryConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelemetryConfig {

    @Bean
    public TelemetryClient telemetryClient(
            @Value("${azure.appinsight.instrumentationkey}") String instrumentationKey) {
        // Configurar la clave de instrumentación
        TelemetryConfiguration configuration = TelemetryConfiguration.getActive();
        configuration.setInstrumentationKey("a285f386-66b5-4fd3-9b63-7c75d25c71fc");

        // Crear y devolver el cliente de telemetría
        return new TelemetryClient(configuration);
    }
}
