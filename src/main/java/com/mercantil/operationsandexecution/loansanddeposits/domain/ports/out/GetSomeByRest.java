package com.mercantil.operationsandexecution.loansanddeposits.domain.ports.out;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.SomeByRestModel;
import reactor.core.publisher.Mono;

public interface GetSomeByRest {
    Mono<SomeByRestModel> getSomeByRest();
}
