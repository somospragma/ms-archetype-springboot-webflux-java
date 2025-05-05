package com.mercantil.operationsandexecution.infrastructure.adapter.restclient;

import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface IGenericRestClient {

    <T> Mono<T> exchange(String url, String method, Object request, Class<T> responseType,
                         MultiValueMap<String, String> headers);

}
