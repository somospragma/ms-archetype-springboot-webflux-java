package com.mercantil.operationsandexecution.infrastructure.adapter.mongo.mapper;

import com.mercantil.operationsandexecution.infrastructure.adapter.mongo.entity.LoanLimitsData;
import com.mercantil.operationsandexecution.domain.models.LoanLimitInfo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanLimitMapper {

    public static LoanLimitInfo toModel(LoanLimitsData data){
        return new LoanLimitInfo(data.getLoanLimit());
    }
}
