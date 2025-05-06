package com.mercantil.operationsandexecution.infrastructure.mapper;

import com.mercantil.operationsandexecution.domain.models.LoanLimitInfo;
import com.mercantil.operationsandexecution.entity.LoanLimitsData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanLimitMapper {

    public static LoanLimitInfo toModel(LoanLimitsData data){
        return new LoanLimitInfo(data.getLoanLimit());
    }
}
