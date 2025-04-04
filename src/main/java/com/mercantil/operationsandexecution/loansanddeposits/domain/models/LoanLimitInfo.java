package com.mercantil.operationsandexecution.loansanddeposits.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class LoanLimitInfo {
    private BigDecimal maxLoanInfo;
}
