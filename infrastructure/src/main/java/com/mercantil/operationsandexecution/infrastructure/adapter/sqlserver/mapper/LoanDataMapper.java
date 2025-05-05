package com.mercantil.operationsandexecution.infrastructure.adapter.sqlserver.mapper;

import com.mercantil.operationsandexecution.infrastructure.adapter.sqlserver.data.LoanData;
import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanDataMapper {

    public static LoanData toData(LoanCreate loanCreate){
        return LoanData.builder()
                .id(1)
                .amount(loanCreate.getAmount())
                .interestRate(loanCreate.getInterestRate())
                .build();
    }
}
