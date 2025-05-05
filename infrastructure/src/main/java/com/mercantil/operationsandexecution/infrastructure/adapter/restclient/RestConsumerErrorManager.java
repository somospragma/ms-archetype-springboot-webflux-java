package com.mercantil.operationsandexecution.infrastructure.adapter.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mercantil.operationsandexecution.domain.exception.AppException;
import com.mercantil.operationsandexecution.domain.exception.ConstantException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestConsumerErrorManager {
    private final ObjectMapper objectMapper;
    private static final String REPLACE = "[\\n\\r\\t]";
    private static final String EMPTY_VALUE = "";

    public Mono<AppException> buildException(ClientResponse clientResponse, String msgId,
                                             ConstantException constantException){
        return clientResponse.bodyToMono(String.class)
                .flatMap(body -> {
                    try {
                        var bodyReplace = body.replaceAll(REPLACE, EMPTY_VALUE);
                        var map = objectMapper.readValue(bodyReplace, Map.class);
                        return Mono.error(new AppException(constantException,
                                objectMapper.writeValueAsString(map)));
                    } catch  (RuntimeException | JsonProcessingException e) {
                        return Mono.error(new AppException(constantException, body));
                    }
                });
    }
}
