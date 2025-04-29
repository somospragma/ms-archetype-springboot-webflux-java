package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.routerhandler;

import com.mercantil.operationsandexecution.loansanddeposits.application.usecase.LoanCreateUseCase;
import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.ConstEntryPointRest;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router.model.CreateLoanReqFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.support.ServerRequestWrapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router.model.EntryPointConstantTests.MESSAGE_ID_VALUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateLoanHandlerTest {

    @Mock
    ServerRequest mockRequest;
    ServerRequestWrapper wrapper;
    @Mock
    ServerRequest.Headers headers;
    @Mock
    LoanCreateUseCase loanCreateUseCase;

    @InjectMocks
    CreateLoanHandler createLoanHandler;

    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
        wrapper = new ServerRequestWrapper(mockRequest);
        when(mockRequest.headers()).thenReturn(headers);
    }

    @Test
    void testCreateLoanHandle(){
        //Given
        var createLoanBodyReq = CreateLoanReqFactory.build();

        //when
        when(mockRequest.headers().firstHeader(ConstEntryPointRest.HeaderKeys.MESSAGE_ID))
                .thenReturn(MESSAGE_ID_VALUE);
        when(mockRequest.bodyToMono(LoanCreate.class))
                .thenReturn(Mono.just(createLoanBodyReq));
        when(loanCreateUseCase.create(any()))
                .thenReturn(Mono.empty());

        //then
        createLoanHandler.handle(mockRequest)
                .as(StepVerifier::create)
                .expectNextMatches(serverResponse ->
                        HttpStatus.CREATED.equals(serverResponse.statusCode()))
                .verifyComplete();
    }

}
