package com.mercantil.operationsandexecution.infrastructure.router;

import com.mercantil.operationsandexecution.infrastructure.routerhandler.CreateLoanHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CreateLoanRouter {

    private final CreateLoanHandler createLoanHandler;

    @Bean(name = "createLoanRouterFunction")
    public RouterFunction<ServerResponse> routerFunction() {
        log.info("Initializing CreateLoanRouter with path: /loan");
        return RouterFunctions.route()
                .POST("/loan", createLoanHandler::handle)
                .build();
    }
}
