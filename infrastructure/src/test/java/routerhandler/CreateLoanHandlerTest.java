package routerhandler;


import com.mercantil.operationsandexecution.application.usecase.LoanCreateUseCase;
import com.mercantil.operationsandexecution.infrastructure.rest.model.ConstEntryPointRest;
import com.mercantil.operationsandexecution.infrastructure.rest.model.request.CreateLoanBodyReq;
import com.mercantil.operationsandexecution.infrastructure.rest.routerhandler.CreateLoanHandler;
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
import router.model.CreateLoanReqFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static router.model.EntryPointConstantTests.MESSAGE_ID_VALUE;

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
        when(mockRequest.bodyToMono(CreateLoanBodyReq.class))
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
