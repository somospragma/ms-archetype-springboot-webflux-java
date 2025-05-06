package com.mercantil.operationsandexecution.infrastructure.config;


import com.mercantil.operationsandexecution.infrastructure.model.ConsumeParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient createWebClient(){
        return WebClient.builder().build();
    }

    @Bean
    public ConsumeParams loadConsumeParams(
            @Value("${adapter.restconsumer.anyrestconsume.baseurl}") String baseUrl,
            @Value("${adapter.restconsumer.anyrestconsume.somepath}") String path){
        return new ConsumeParams(baseUrl, path);
    }
}
