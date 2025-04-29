package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.routerhandler;

import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.mapper.LoanMapper;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.BaseSuccessResponse;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.ConstEntryPointRest;
import com.mercantil.operationsandexecution.loansanddeposits.application.usecase.LoanCreateUseCase;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.request.CreateLoanBodyReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateLoanHandler {
    private final LoanCreateUseCase loanCreateUseCase;

    public Mono<ServerResponse> handle(ServerRequest serverRequest){
        // TODO: 22/01/25  Agregar headers mandatorios.
        return serverRequest.bodyToMono(CreateLoanBodyReq.class)
                .map(LoanMapper::toModel)
                .flatMap(loanCreateUseCase::create)
                .then(Mono.defer(() -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(BaseSuccessResponse.createSuccessResponse(HttpStatus.CREATED.toString(),
                                        serverRequest.headers().firstHeader(ConstEntryPointRest.HeaderKeys.MESSAGE_ID))),
                                BaseSuccessResponse.class)));
    }
}
