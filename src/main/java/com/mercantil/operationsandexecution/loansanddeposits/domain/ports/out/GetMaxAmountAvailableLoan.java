package com.mercantil.operationsandexecution.loansanddeposits.domain.ports.out;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanLimitInfo;
import reactor.core.publisher.Mono;

public interface GetMaxAmountAvailableLoan {

    Mono<LoanLimitInfo> get(String typeLoan);
}
