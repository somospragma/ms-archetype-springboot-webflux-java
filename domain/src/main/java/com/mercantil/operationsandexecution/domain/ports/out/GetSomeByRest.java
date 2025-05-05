package com.mercantil.operationsandexecution.domain.ports.out;

import com.mercantil.operationsandexecution.domain.models.SomeByRest;
import reactor.core.publisher.Mono;

public interface GetSomeByRest {
    Mono<SomeByRest> getSomeByRest();
}
