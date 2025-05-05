package com.mercantil.operationsandexecution.infrastructure.adapter.azurelogging;


import com.mercantil.operationsandexecution.infrastructure.adapter.azurelogging.messages.MessageType;
import com.mercantil.operationsandexecution.domain.models.EnumMsAction;
import com.mercantil.operationsandexecution.domain.models.metainfo.ContextDataKey;
import com.mercantil.operationsandexecution.domain.models.metainfo.MetaData;
import com.mercantil.operationsandexecution.domain.ports.out.OutLoggerRegistry;
import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.EventTelemetry;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;
import com.microsoft.applicationinsights.telemetry.TraceTelemetry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoggingImpl implements OutLoggerRegistry {
    private final TelemetryClient telemetryClient;
    private static final Map<MessageType, SeverityLevel> SEVERITY_LEVELS = Map.of(
            MessageType.INFO, SeverityLevel.Information,
            MessageType.WARNING, SeverityLevel.Warning,
            MessageType.ERROR, SeverityLevel.Error,
            MessageType.SUCCESS, SeverityLevel.Information
    );

    public static final String LOG_TRANSACTION_ID = "transactionId = ";
    public static final String LOG_MESSAGE = "message = ";
    public static final String LOG_ADDITIONAL_DATA = "additionalData = ";
    public static final String LOG_SPACE = ", ";
    public static final String SERVICE_REQUESTED = "serviceRequested = ";


    @Override
    public Mono<Void> logTraceError(String transactionId, String message, Object additionalData) {
        return Mono.deferContextual(contextView -> {
            MetaData metaData = contextView.get(ContextDataKey.META_DATA);
            logMessage(MessageType.ERROR, buildTraceMessage(metaData.getTransactionId(), message, additionalData).toString());
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> logEventSuccess(String transactionId, EnumMsAction msAction, String additionalData) {
        logEvent(buildEventMessage(transactionId, msAction, additionalData).toString());
        return Mono.empty();
    }

    private SeverityLevel getSeverityLevel(MessageType type) {
        return SEVERITY_LEVELS.getOrDefault(type, SeverityLevel.Verbose);
    }

    private void logMessage(MessageType type, String message) {
        TraceTelemetry telemetry = new TraceTelemetry();
        telemetry.setMessage(message);
        telemetry.setSeverityLevel(getSeverityLevel(type));
        telemetryClient.trackTrace(telemetry);
    }

    private void logEvent(String message) {
        EventTelemetry eventTelemetry = new EventTelemetry(message);
        telemetryClient.trackEvent(eventTelemetry);
    }

    private StringBuilder buildEventMessage(String transactionId, EnumMsAction msActions, String entryData) {
        StringBuilder eventMessage = new StringBuilder();
        eventMessage.append(LOG_TRANSACTION_ID).append(transactionId).append(LOG_SPACE);
        eventMessage.append(SERVICE_REQUESTED).append(msActions.name()).append(LOG_SPACE);
        eventMessage.append(LOG_ADDITIONAL_DATA).append(entryData);

        return eventMessage;
    }

    private StringBuilder buildTraceMessage(String transactionId, String message, Object additionalData) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(LOG_TRANSACTION_ID).append(transactionId).append(LOG_SPACE);
        logMessage.append(LOG_MESSAGE).append(message).append(LOG_SPACE);
        logMessage.append(LOG_ADDITIONAL_DATA).append(additionalData);

        return logMessage;
    }
}
