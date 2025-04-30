package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver.mapper;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver.data.LoanData;
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
