package com.mercantil.operationsandexecution.loansanddeposits.domain.ports.out;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.SomeByRest;
import reactor.core.publisher.Mono;

public interface GetSomeByRest {
    Mono<SomeByRest> getSomeByRest();
}
