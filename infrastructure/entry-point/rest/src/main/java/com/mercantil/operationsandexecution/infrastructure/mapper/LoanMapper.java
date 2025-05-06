package com.mercantil.operationsandexecution.infrastructure.mapper;


import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.infrastructure.model.request.CreateLoanBodyReq;
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
