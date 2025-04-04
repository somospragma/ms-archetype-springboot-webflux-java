package com.mercantil.operationsandexecution.crosscutting.events.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceBusSecret {
    private String connectionString;
}
