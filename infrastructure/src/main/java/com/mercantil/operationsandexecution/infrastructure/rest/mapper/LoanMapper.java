package com.mercantil.operationsandexecution.infrastructure.rest.mapper;


import com.mercantil.operationsandexecution.infrastructure.rest.model.request.CreateLoanBodyReq;
import com.mercantil.operationsandexecution.domain.models.LoanCreate;
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
