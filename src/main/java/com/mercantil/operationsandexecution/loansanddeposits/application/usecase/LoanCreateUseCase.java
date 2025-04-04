package com.mercantil.operationsandexecution.loansanddeposits.application.usecase;

import com.mercantil.operationsandexecution.crosscutting.application.models.labels.UseCase;
import com.mercantil.operationsandexecution.crosscutting.application.usecase.LoggerRegistryUseCase;
import com.mercantil.operationsandexecution.loansanddeposits.application.usecase.mapper.LoanMapper;
import com.mercantil.operationsandexecution.loansanddeposits.domain.ports.in.ICreateLoan;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.request.CreateLoanBodyReq;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class LoanCreateUseCase {

    private final ICreateLoan createLoan;
    private final LoggerRegistryUseCase loggerRegistryUseCase;

    public Mono<Void> create(CreateLoanBodyReq createLoanBodyReq){
        return createLoan.createLoan(LoanMapper.toModel(createLoanBodyReq))
                .then(loggerRegistryUseCase.registryEvent("MetaData", "Consulta de usuario exitosa",
                        createLoanBodyReq));
    }
}
