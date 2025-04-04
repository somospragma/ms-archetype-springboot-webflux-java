package com.mercantil.operationsandexecution.crosscutting.logging;

import com.mercantil.operationsandexecution.crosscutting.domain.models.EnumMsAction;
import reactor.core.publisher.Mono;

public interface ILoggerService {

    Mono<Void> logTraceInfo(EnumMsAction enumMsAction, String message, Object additionalData);
    Mono<Void> logExternalConsume(EnumMsAction enumMsAction, String url, Object sanitizedBody);
    Mono<Void> logEvent(EnumMsAction msAction, String eventName, String message, String additionalData);

    Mono<Void> logError(EnumMsAction enumMsAction, Throwable throwable);
    Mono<Void> logWarning(EnumMsAction enumMsAction, Throwable throwable);
    Mono<Void> logDebug(EnumMsAction enumMsAction, String message, Object additionalData);
}
