package com.mercantil.operationsandexecution.loansanddeposits.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class LoanCreate {
    private String nameUser;
    private String loanType;
    private BigDecimal amount;
    private Double interestRate;
}
