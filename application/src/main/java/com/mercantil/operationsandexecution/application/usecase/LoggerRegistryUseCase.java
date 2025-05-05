package com.mercantil.operationsandexecution.application.usecase;

import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoggerRegistryUseCase {


    public Mono<Void> registryEvent(String metaData, String consultaDeUsuarioExitosa, LoanCreate createLoanBodyReq) {
        return Mono.empty(); //Pending implementation
    }
}
