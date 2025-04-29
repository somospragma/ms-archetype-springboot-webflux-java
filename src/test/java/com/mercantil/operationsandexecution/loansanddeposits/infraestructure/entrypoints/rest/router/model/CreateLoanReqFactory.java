package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.router.model;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;

import java.math.BigDecimal;

public class CreateLoanReqFactory {
    public static LoanCreate build(){
        return new LoanCreate("Roberto Carlos", "HIPOTECARIO",
                new BigDecimal(1000), 1.4);
    }

    public static String buildJsonResponse(String date) {
        return "{"
                + "\"statusCode\":\"201 CREATED\","
                + "\"status\":\"SUCCESS\","
                + "\"message\":\"Creación exitosa\","
                + "\"timestamp\":\"" + date + "\","
                + "\"transactionId\":\"" + EntryPointConstantTests.MESSAGE_ID_VALUE + "\""
                + "}";
    }
}
