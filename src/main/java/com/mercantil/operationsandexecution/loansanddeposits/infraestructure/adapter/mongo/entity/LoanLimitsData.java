package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loan_Limits")
public class LoanLimitsData {
    private String id;
    private BigDecimal loanLimit;
    private String loanType;
}
