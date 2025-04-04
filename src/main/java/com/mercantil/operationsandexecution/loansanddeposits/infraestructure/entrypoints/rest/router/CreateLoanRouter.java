package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router;

import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.routerhandler.CreateLoanHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateLoanRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(@Value("${app.url.createloan}") String route,
                                                         CreateLoanHandler handler){
        return route(POST(route), handler::handle);
    }
}
