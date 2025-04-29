package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.mapper;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.entrypoints.rest.model.request.CreateLoanBodyReq;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanMapper {

    public static LoanCreate toModel(CreateLoanBodyReq request){
        return new LoanCreate(
                request.getNameUser(),
                request.getLoanType(),
                request.getAmount(),
                request.getInterestRate());
    }
}
