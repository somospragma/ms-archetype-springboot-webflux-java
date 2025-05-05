package com.mercantil.operationsandexecution.domain.ports.out;

import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import reactor.core.publisher.Mono;

public interface SaveLoanPersistence {

    Mono<Void> saveLoanRepository(LoanCreate loanCreate);
}
