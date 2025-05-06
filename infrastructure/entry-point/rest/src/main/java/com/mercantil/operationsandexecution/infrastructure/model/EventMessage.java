package com.mercantil.operationsandexecution.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventMessage {
    private String eventName;
    private Object eventPayload;
}
