package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.config;


import com.mercantil.operationsandexecution.loansanddeposits.domain.exception.AppException;
import com.mercantil.operationsandexecution.loansanddeposits.domain.exception.ConstantException;
import com.mercantil.operationsandexecution.loansanddeposits.domain.models.EnumMsAction;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.metainfo.ContextDataKey;
import com.mercantil.operationsandexecution.loansanddeposits.domain.models.metainfo.MetaData;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.ConstEntryPointRest;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.GlobalErrorResponse;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.errorlogging.ILoggerService;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;


@Component
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {
    private final ILoggerService loggerRegistry;
    private static final String HEADER_NOT_SPECIFIED = "HEADER_NOT_SPECIFIED";

    public GlobalExceptionHandler(ErrorAttributes errorAttributes, ApplicationContext applicationContext,
                                  ServerCodecConfigurer serverCodecConfigurer, ILoggerService loggerRegistry) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        this.loggerRegistry = loggerRegistry;
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderException);
    }

    private Mono<ServerResponse> renderException(ServerRequest serverRequest){
        return Mono.just(getError(serverRequest))
                .flatMap(Mono::error)
                .onErrorResume(AppException.class, exception -> buildManagedError(exception,
                        serverRequest))
                .onErrorResume(Throwable.class, exception -> buildUnknownError(serverRequest, exception))
                .cast(ServerResponse.class);
    }

    private Mono<ServerResponse> buildManagedError(AppException exception, ServerRequest request) {
        ConstantException error = exception.getConstantException();
        String transactionId = request.headers().firstHeader(ConstEntryPointRest.HeaderKeys.TRANSACTION_ID) == null ?
                HEADER_NOT_SPECIFIED : request.headers().firstHeader(ConstEntryPointRest.HeaderKeys.TRANSACTION_ID);
        return loggerRegistry.logError(EnumMsAction.NOT_IDENTIFIED, exception)
                .contextWrite(Context.of(ContextDataKey.META_DATA, new MetaData(transactionId)))
                .then(ServerResponse
                        .status(HttpStatus.valueOf(error.getHttpStatus()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(GlobalErrorResponse.create(exception.getConstantException(), transactionId,
                                        exception.getClass().getSimpleName())),
                                GlobalErrorResponse.class));
    }

    private Mono<ServerResponse> buildUnknownError(ServerRequest serverRequest, Throwable ex) {
        String transactionId = serverRequest.headers().firstHeader(ConstEntryPointRest.HeaderKeys.TRANSACTION_ID) == null ?
                HEADER_NOT_SPECIFIED : serverRequest.headers().firstHeader(ConstEntryPointRest.HeaderKeys.TRANSACTION_ID);
        return loggerRegistry.logError(EnumMsAction.NOT_IDENTIFIED, ex)
                .contextWrite(Context.of(ContextDataKey.META_DATA, new MetaData(transactionId)))
                .then(ServerResponse
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Mono.just(GlobalErrorResponse.create(ConstantException.INTERNAL_SERVER_ERROR,
                                transactionId, ex.getClass().getSimpleName())), GlobalErrorResponse.class));
    }

}
