package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.dataproviders.database.sqlserver.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Table("loananddeposits.loan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanData {
    private Integer id;
    private BigDecimal amount;
    private double interestRate;
    private Timestamp startDate;
    private Timestamp endDate;
}
