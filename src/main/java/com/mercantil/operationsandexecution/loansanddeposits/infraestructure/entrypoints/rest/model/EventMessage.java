package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventMessage {
    private String eventName;
    private Object eventPayload;
}
