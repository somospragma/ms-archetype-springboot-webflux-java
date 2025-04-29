package com.mercantil.operationsandexecution.loansanddeposits.application.usecase;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import reactor.core.publisher.Mono;

public class LoggerRegistryUseCase {


    public Mono<Void> registryEvent(String metaData, String consultaDeUsuarioExitosa, LoanCreate createLoanBodyReq) {
        return Mono.empty(); //Pending implementation
    }
}
