package com.mercantil.operationsandexecution.crosscutting.logging;

import com.google.gson.Gson;
import com.mercantil.operationsandexecution.crosscutting.domain.models.EnumMsAction;
import com.mercantil.operationsandexecution.crosscutting.domain.models.metainfo.ContextDataKey;
import com.mercantil.operationsandexecution.crosscutting.domain.models.metainfo.MetaData;
import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.EventTelemetry;
import com.microsoft.applicationinsights.telemetry.ExceptionTelemetry;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;
import com.microsoft.applicationinsights.telemetry.TraceTelemetry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoggerService implements ILoggerService {
    private final TelemetryClient telemetryClient;
    private final Gson gson;

    public static final String LOG_TRANSACTION_ID = "transactionId = ";
    public static final String EXECUTED_SERVICE = "Servicio ejecutado: ";
    public static final String ENTRY_DATA = "Data de entrada: ";
    public static final String BEGIN_PROCESS = "Se inicia proceso: ";
    private static final String END_PROCESS_SUCCESS = "Proceso finalizado satisfactoriamente: ";
    private static final String EXTERNAL_CONSUME_MESSAGE = "Se consume servicio externo: ";
    public static final String LOG_ERROR_MESSAGE = "error message = ";
    public static final String LOG_ADDITIONAL_DATA = "additionalData = ";
    public static final String MESSAGE = "Message = ";
    public static final String MESSAGE_ERROR = "Mensaje de error: ";
    public static final String LOG_SPACE = ", ";
    public static final String TRANSACTION_ID_CONSTANT = "TransactionId";
    public static final String TRANSACTION_ID_NOT_SPECIFIED ="NOT_SPECIFIED";
    public static final String NA_CONSTANT = "N/A";

    @Override
    public Mono<Void> logTraceInfo(EnumMsAction enumMsAction, String message,
                                   Object additionalData) {
        return logTraceTelemetry(enumMsAction, message, gson.toJson(additionalData));
    }

    @Override
    public Mono<Void> logExternalConsume(EnumMsAction enumMsAction, String url, Object sanitizedBody) {
        String externalConsumeMessage = EXTERNAL_CONSUME_MESSAGE.concat(url);

        return logTraceTelemetry(enumMsAction, externalConsumeMessage, gson.toJson(sanitizedBody));
    }

    @Override
    public Mono<Void> logEvent(EnumMsAction msAction, String eventName, String message, String additionalData) {
        return logEventTelemetry(msAction, eventName, message,additionalData);
    }

    @Override
    public Mono<Void> logDebug(EnumMsAction enumMsAction, String message, Object additionalData) {
        return Mono.fromRunnable(() -> log.log(Level.DEBUG, formatMessageForTraceAndInternalLog(
                TRANSACTION_ID_NOT_SPECIFIED, enumMsAction, message, gson.toJson(additionalData))))
                .then();
    }

    @Override
    public Mono<Void> logWarning(EnumMsAction enumMsAction, Throwable throwable) {
        return logExceptionTelemetry(enumMsAction, throwable, gson.toJson(throwable.getMessage()), Level.WARN);
    }

    @Override
    public Mono<Void> logError(EnumMsAction enumMsAction, Throwable throwable) {
        return logExceptionTelemetry(enumMsAction, throwable, gson.toJson(throwable.getMessage()), Level.ERROR);
    }

    private Mono<Void> logWithType(Level type, String messageFormatted) {
        return Mono.fromRunnable(() -> log.log(type, messageFormatted));
    }

    private Mono<Void> logTraceTelemetry(EnumMsAction enumMsAction, String message,
                                String additionalData) {
        var data = additionalData != null ? gson.toJson(additionalData) : NA_CONSTANT;
        return Mono.deferContextual(contextView -> {
            MetaData metaData = contextView.get(ContextDataKey.META_DATA);
            return Mono.fromCallable(() -> {
                TraceTelemetry trace = new TraceTelemetry();
                String messageFormatted = formatMessageForTraceAndInternalLog(metaData.getTransactionId(),
                        enumMsAction, message, data);
                trace.setMessage(formatMessageForTraceAndInternalLog(metaData.getTransactionId(),
                        enumMsAction, messageFormatted, data));

                trace.setSeverityLevel(SeverityLevel.Information);
                trace.getProperties().put(TRANSACTION_ID_CONSTANT, metaData.getTransactionId());
                telemetryClient.trackTrace(trace);

                return messageFormatted;
            }).flatMap(messageFormatted -> logWithType(Level.INFO, messageFormatted));
        });
    }

    private Mono<Void> logEventTelemetry(EnumMsAction enumMsAction, String eventName, String message,
                                         String additionalData) {
        String data = additionalData != null ? gson.toJson(additionalData) : NA_CONSTANT;
        return Mono.deferContextual(contextView ->
            Mono.fromCallable(() -> {
                MetaData metaData = contextView.get(ContextDataKey.META_DATA);
                EventTelemetry event = new EventTelemetry(eventName);
                populatePropsEventAndException(event.getProperties(), metaData.getTransactionId(),
                        enumMsAction, message, data);

                telemetryClient.trackEvent(event);

                return metaData.getTransactionId();
            }).flatMap(transactionId -> logWithType(Level.INFO, message))
        );
    }

    private Mono<Void> logExceptionTelemetry(EnumMsAction enumMsAction, Throwable throwable,
                                             String additionalData, Level level) {
        String data = additionalData != null ? gson.toJson(additionalData) : NA_CONSTANT;
        return Mono.deferContextual(contextView -> {
            MetaData metaData = contextView.get(ContextDataKey.META_DATA);
            ExceptionTelemetry exception = new ExceptionTelemetry(throwable);
            populatePropsEventAndException(exception.getProperties(), metaData.getTransactionId(),
                    enumMsAction, throwable.getMessage(), data);
            exception.setSeverityLevel(SeverityLevel.Error);
            telemetryClient.trackException(exception);
            log.error(buildProcessEndSuccessMsg(metaData.getTransactionId(),
                    enumMsAction.getLogString()).toString());
            return Mono.just(throwable.getMessage() != null ? throwable.getMessage() : "Unknown error");
        }).flatMap(errorMessage -> logWithType(level, errorMessage));
    }

    private void populatePropsEventAndException(Map<String, String> properties, String transactionId,
                                             EnumMsAction action, String message, Object additionalData) {

        properties.put(TRANSACTION_ID_CONSTANT, transactionId);
        properties.put(EXECUTED_SERVICE, action.getLogString());
        properties.put(MESSAGE, "message");
        properties.put(LOG_ADDITIONAL_DATA, additionalData.toString());
    }



    private StringBuilder buildProcessEndSuccessMsg(String transactionId, String executedService) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(LOG_TRANSACTION_ID).append(transactionId).append(LOG_SPACE);
        logMessage.append(END_PROCESS_SUCCESS).append(executedService);

        return logMessage;
    }

    private String formatMessageForTraceAndInternalLog(String transactionId, EnumMsAction action, String message,
                                                       String additionalData) {
        String transactionIdValue = (transactionId != null && !transactionId.isEmpty()) ? transactionId :
                TRANSACTION_ID_NOT_SPECIFIED;
        StringBuilder logMessage = new StringBuilder()
                .append(TRANSACTION_ID_CONSTANT).append(transactionIdValue).append(LOG_SPACE)
                .append(EXECUTED_SERVICE).append(action.getLogString()).append(LOG_SPACE)
                .append(MESSAGE).append(message);

        if (additionalData != null && !additionalData.equals("")) {
            logMessage.append(LOG_SPACE).append(LOG_ADDITIONAL_DATA).append(additionalData);
        }

        return logMessage.toString();
    }
}