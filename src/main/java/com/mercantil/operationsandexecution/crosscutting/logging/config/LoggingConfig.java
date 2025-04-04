package com.mercantil.operationsandexecution.crosscutting.logging.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.TelemetryConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class LoggingConfig {

    @Bean
    public TelemetryClient telemetryClient(
            @Value("${azure.appinsight.instrumentationkey}") String instrumentationKey,
            @Value("${spring.application.name}") String msName) {
        // Configurar la clave de instrumentación
        TelemetryConfiguration configuration = TelemetryConfiguration.getActive();
        configuration.setInstrumentationKey(instrumentationKey);
        configuration.setRoleName(msName);

        // Crear y devolver el cliente de telemetría
        return new TelemetryClient(configuration);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>)
                        (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.toString()))
                .create();
    }

}
