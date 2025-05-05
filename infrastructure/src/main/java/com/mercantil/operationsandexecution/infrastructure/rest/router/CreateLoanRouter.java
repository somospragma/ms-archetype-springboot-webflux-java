package com.mercantil.operationsandexecution.infrastructure.rest.router;

import com.mercantil.operationsandexecution.infrastructure.rest.routerhandler.CreateLoanHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateLoanRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(CreateLoanHandler handler){
        return route(POST("loan"), handler::handle);
    }

}
