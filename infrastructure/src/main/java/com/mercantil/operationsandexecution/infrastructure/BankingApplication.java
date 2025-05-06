package com.mercantil.operationsandexecution.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.mercantil.operationsandexecution.infrastructure",
		"com.mercantil.operationsandexecution.application",
		"com.mercantil.operationsandexecution.domain",
		"com.mercantil.operationsandexecution"
})
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}
}
