package com.mercantil.operationsandexecution.domain.ports.out;

import com.mercantil.operationsandexecution.domain.models.LoanLimitInfo;
import reactor.core.publisher.Mono;

public interface GetMaxAmountAvailableLoan {

    Mono<LoanLimitInfo> get(String typeLoan);
}
