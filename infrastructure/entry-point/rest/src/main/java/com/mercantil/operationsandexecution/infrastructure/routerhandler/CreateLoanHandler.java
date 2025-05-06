package com.mercantil.operationsandexecution.infrastructure.routerhandler;


import com.mercantil.operationsandexecution.application.usecase.LoanCreateUseCase;
import com.mercantil.operationsandexecution.infrastructure.mapper.LoanMapper;
import com.mercantil.operationsandexecution.infrastructure.model.BaseSuccessResponse;
import com.mercantil.operationsandexecution.infrastructure.model.ConstEntryPointRest;
import com.mercantil.operationsandexecution.infrastructure.model.request.CreateLoanBodyReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateLoanHandler {

    private final LoanCreateUseCase loanCreateUseCase;

    public Mono<ServerResponse> handle(ServerRequest serverRequest){
        return serverRequest.bodyToMono(CreateLoanBodyReq.class)
                .map(LoanMapper::toModel)
                .doOnNext(loanCreate -> log.info("Creando prestamo con id: {}"))
                .flatMap(loanCreateUseCase::create)
                //.doOnNext(loanCreate -> log.info("Prestamo creado con id: {}", loanCreate.getId()))
                .then(Mono.defer(() -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(BaseSuccessResponse.createSuccessResponse(HttpStatus.CREATED.toString(),
                                        serverRequest.headers().firstHeader(ConstEntryPointRest.HeaderKeys.MESSAGE_ID))),
                                BaseSuccessResponse.class)));
    }
}
