package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo.mapper;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanLimitInfo;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo.entity.LoanLimitsData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanLimitMapper {

    public static LoanLimitInfo toModel(LoanLimitsData data){
        return new LoanLimitInfo(data.getLoanLimit());
    }
}
