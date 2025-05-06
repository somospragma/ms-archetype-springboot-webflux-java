package services.util;

import com.mercantil.operationsandexecution.domain.models.LoanCreate;

import java.math.BigDecimal;

public class DomainTestUtil {

    public static LoanCreate buildLoanCreate() {
        return LoanCreate.builder()
                .nameUser("Test")
                .loanType("Test")
                .amount(new BigDecimal("1000.00"))
                .interestRate(5.0)
                .build();
    }

}
