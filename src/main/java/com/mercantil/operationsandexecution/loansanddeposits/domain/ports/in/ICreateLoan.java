package com.mercantil.operationsandexecution.loansanddeposits.domain.ports.in;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import reactor.core.publisher.Mono;

public interface ICreateLoan {

    Mono<Void> createLoan(LoanCreate loanCreate);
}
