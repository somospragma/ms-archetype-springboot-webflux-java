package com.mercantil.operationsandexecution.loansanddeposits.application.usecase;

import com.mercantil.operationsandexecution.loansanddeposits.domain.ports.in.ICreateLoan;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router.model.CreateLoanReqFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoanCreateUseCaseTest {

    @Mock
    ICreateLoan iCreateLoan;
    @Mock
    LoggerRegistryUseCase loggerRegistryUseCase;

    @InjectMocks
    LoanCreateUseCase loanCreateUseCase;

    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoanSuccess() {
        //given
        var loanCreate = CreateLoanReqFactory.build();

        //when
        when(iCreateLoan.createLoan(any())).thenReturn(Mono.empty());
        when(loggerRegistryUseCase.registryEvent(any(), any(), any()))
                .thenReturn(Mono.empty());

        //
        loanCreateUseCase.create(loanCreate)
                .as(StepVerifier::create)
                .verifyComplete();

    }
}
