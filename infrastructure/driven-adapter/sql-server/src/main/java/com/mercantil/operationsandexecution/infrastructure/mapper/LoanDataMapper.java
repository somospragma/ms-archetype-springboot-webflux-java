package com.mercantil.operationsandexecution.infrastructure.mapper;

import com.mercantil.operationsandexecution.infrastructure.data.LoanData;
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
