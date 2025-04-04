package com.mercantil.operationsandexecution.loansanddeposits.domain.ports.out;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import reactor.core.publisher.Mono;

public interface SaveLoanPersistence {

    Mono<Void> saveLoanRepository(LoanCreate loanCreate);
}
