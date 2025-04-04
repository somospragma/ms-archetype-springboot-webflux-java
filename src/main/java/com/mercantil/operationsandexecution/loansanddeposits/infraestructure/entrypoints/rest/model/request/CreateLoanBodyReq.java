package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoanBodyReq {
    private String nameUser;
    private String loanType;
    private BigDecimal amount;
    private Double interestRate;
}
