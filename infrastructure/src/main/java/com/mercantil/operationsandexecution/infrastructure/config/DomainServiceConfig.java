package com.mercantil.operationsandexecution.infrastructure.config;

import com.mercantil.operationsandexecution.domain.models.labels.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.mercantil.operationsandexecution",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = DomainService.class),
        useDefaultFilters = false)
public class DomainServiceConfig {
}
