package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.config;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.metainfo.MetaData;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.ConstEntryPointRest;
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
