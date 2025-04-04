package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercantil.operationsandexecution.crosscutting.infraestructure.entrypoints.rest.model.BaseSuccessResponse;
import com.mercantil.operationsandexecution.crosscutting.infraestructure.entrypoints.rest.model.ConstEntryPointRest;
import com.mercantil.operationsandexecution.loansanddeposits.application.usecase.LoanCreateUseCase;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.request.CreateLoanBodyReq;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router.model.CreateLoanReqMother;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router.model.EntryPointConstantTests;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.routerhandler.CreateLoanHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@TestPropertySource(properties = {"app.url.createloan=/create"})
@ContextConfiguration(classes = {CreateLoanRouter.class, CreateLoanHandler.class})
class CreateLoanRouterTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    LoanCreateUseCase loanCreateUseCase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        when(loanCreateUseCase.create(any())).thenReturn(Mono.empty());
    }

    @Test
    void testValidateEndpointSignature(){
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        CreateLoanBodyReq mockRequest = CreateLoanReqMother.build();

        // Then
        webTestClient.post()
                .uri(EntryPointConstantTests.URL)
                .headers(buildHeaders())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mockRequest), CreateLoanBodyReq.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BaseSuccessResponse.class)
                .consumeWith(apiResponse -> {
                    BaseSuccessResponse response = objectMapper.convertValue(Objects.requireNonNull(
                            apiResponse.getResponseBody()), BaseSuccessResponse.class);

                    String expectedResponse = CreateLoanReqMother.buildJsonResponse(response.getTimestamp());
                    try {
                        var responseJson = objectMapper.writeValueAsString(response);

                        Assertions.assertEquals(expectedResponse, responseJson);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private Consumer<HttpHeaders> buildHeaders(){
        //Add more headers if necessary.
        return httpHeaders -> {
            httpHeaders.add(ConstEntryPointRest.HeaderKeys.MESSAGE_ID, EntryPointConstantTests.MESSAGE_ID_VALUE);
        };
    }
}