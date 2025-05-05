package com.mercantil.operationsandexecution.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class LoanCreate {
    private String nameUser;
    private String loanType;
    private BigDecimal amount;
    private Double interestRate;
}
