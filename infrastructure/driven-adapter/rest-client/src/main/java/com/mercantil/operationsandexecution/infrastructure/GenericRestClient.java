package com.mercantil.operationsandexecution.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GenericRestClient implements IGenericRestClient{
    private final WebClient webClient;
    @Override
    public <T> Mono<T> exchange(String url, String method, Object request, Class<T> responseType, MultiValueMap<String,
                String> headers) {

        HttpMethod httpMethod = HttpMethod.valueOf(method);
        return webClient
                .method(httpMethod)
                .uri(url)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType);
    }
}
