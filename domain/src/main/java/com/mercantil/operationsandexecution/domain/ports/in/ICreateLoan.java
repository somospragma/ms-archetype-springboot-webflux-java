package com.mercantil.operationsandexecution.domain.ports.in;

import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import reactor.core.publisher.Mono;

public interface ICreateLoan {

    Mono<Void> createLoan(LoanCreate loanCreate);
}
