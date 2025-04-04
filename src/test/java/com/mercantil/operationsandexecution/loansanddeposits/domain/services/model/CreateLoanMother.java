package com.mercantil.operationsandexecution.loansanddeposits.domain.services.model;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;

import java.math.BigDecimal;

public class CreateLoanMother {

    public static LoanCreate createModel(){
        return new LoanCreate("Roberto Carlos", "HIPOTECARIO",
                new BigDecimal(10000), 1.4);
    }
}
