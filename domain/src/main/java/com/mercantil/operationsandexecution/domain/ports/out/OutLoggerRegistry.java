package com.mercantil.operationsandexecution.domain.ports.out;

import com.mercantil.operationsandexecution.domain.models.EnumMsAction;
import reactor.core.publisher.Mono;

public interface OutLoggerRegistry {
    Mono<Void> logTraceError(String transactionId, String message, Object additionalData);
    Mono<Void> logEventSuccess(String transactionId, EnumMsAction msAction, String additionalData);
}
