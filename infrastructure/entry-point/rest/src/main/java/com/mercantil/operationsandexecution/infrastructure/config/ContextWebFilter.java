package com.mercantil.operationsandexecution.infrastructure.config;

import com.mercantil.operationsandexecution.domain.models.metainfo.MetaData;
import com.mercantil.operationsandexecution.infrastructure.model.ConstEntryPointRest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Objects;

@Component
public class ContextWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .contextWrite(Context.of(ConstEntryPointRest.HeaderKeys.TRANSACTION_ID,
                        new MetaData(Objects.requireNonNull(exchange.getRequest()
                                .getHeaders().getFirst(ConstEntryPointRest.HeaderKeys.TRANSACTION_ID)))));
    }

}
