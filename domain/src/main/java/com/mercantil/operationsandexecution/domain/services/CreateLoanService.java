package com.mercantil.operationsandexecution.domain.services;



import com.mercantil.operationsandexecution.domain.exception.AppException;
import com.mercantil.operationsandexecution.domain.exception.ConstantException;
import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.domain.models.labels.DomainService;
import com.mercantil.operationsandexecution.domain.ports.in.ICreateLoan;
import com.mercantil.operationsandexecution.domain.ports.out.GetMaxAmountAvailableLoan;
import com.mercantil.operationsandexecution.domain.ports.out.SaveLoanPersistence;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@DomainService
@RequiredArgsConstructor
public class CreateLoanService implements ICreateLoan {
    private final SaveLoanPersistence saveLoanPersistence;
    private final GetMaxAmountAvailableLoan getMaxAmountAvailableLoan;


    @Override
    public Mono<Void> createLoan(LoanCreate loanCreate) {
        return getMaxAmountAvailableLoan.get(loanCreate.getLoanType())
                .flatMap(limitLoan -> validateLoanLimit(loanCreate.getAmount(),
                        limitLoan.getMaxLoanInfo()))
                .then(Mono.defer(() -> saveLoanPersistence.saveLoanRepository(loanCreate)))
                .then();
    }

    private Mono<Void> validateLoanLimit(BigDecimal amountLoanToCreate, BigDecimal limitLoan){
        return Mono.defer(() -> amountLoanToCreate.compareTo(limitLoan) > 0 ?
                Mono.error(new AppException(ConstantException.LOAN_AMOUNT_EXCEEDS_LIMIT)) : Mono.empty());
    }

}

