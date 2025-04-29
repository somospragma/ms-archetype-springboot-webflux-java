package com.mercantil.operationsandexecution.loansanddeposits.application.usecase;


import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.loansanddeposits.domain.ports.in.ICreateLoan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoanCreateUseCase {

    private final ICreateLoan createLoan;
    private final LoggerRegistryUseCase loggerRegistryUseCase;

    public Mono<Void> create(LoanCreate createLoanBodyReq){
        return createLoan.createLoan(createLoanBodyReq)
                .then(loggerRegistryUseCase.registryEvent("MetaData", "Consulta de usuario exitosa",
                        createLoanBodyReq));
    }
}
