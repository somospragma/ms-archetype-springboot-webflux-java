package com.mercantil.operationsandexecution.crosscutting.application.usecase;

import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.request.CreateLoanBodyReq;
import reactor.core.publisher.Mono;

public class LoggerRegistryUseCase {


    public Mono<Void> registryEvent(String metaData, String consultaDeUsuarioExitosa, CreateLoanBodyReq createLoanBodyReq) {
        return Mono.empty(); //Pending implementation
    }
}
