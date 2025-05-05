package services;


import com.mercantil.operationsandexecution.domain.exception.ConstantException;
import com.mercantil.operationsandexecution.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.domain.models.LoanLimitInfo;
import com.mercantil.operationsandexecution.domain.ports.out.GetMaxAmountAvailableLoan;
import com.mercantil.operationsandexecution.domain.ports.out.SaveLoanPersistence;
import com.mercantil.operationsandexecution.domain.services.CreateLoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import services.util.DomainTestUtil;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

class CreateLoanServiceTest {
    @Mock
    SaveLoanPersistence saveLoanPersistence;
    @Mock
    GetMaxAmountAvailableLoan getMaxAmountAvailableLoan;
    @InjectMocks
    CreateLoanService createLoanService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoanAmountExceedsLimits(){
        //given
        LoanCreate loanCreate = DomainTestUtil.buildLoanCreate();
        LoanLimitInfo loanLimitInfoFromDb = new LoanLimitInfo(new BigDecimal(10));

        //when
        when(getMaxAmountAvailableLoan.get(loanCreate.getLoanType()))
                .thenReturn(Mono.just(loanLimitInfoFromDb));
        when(saveLoanPersistence.saveLoanRepository(loanCreate))
                .thenReturn(Mono.empty());

        //then
        createLoanService.createLoan(loanCreate)
                .as(StepVerifier::create)
                .expectErrorMessage(ConstantException.LOAN_AMOUNT_EXCEEDS_LIMIT.getMessage())
                .verify();
    }

    @Test
    void testLoanCreateSuccess() {
        //given
        LoanCreate loanCreate = DomainTestUtil.buildLoanCreate();
        LoanLimitInfo loanLimitInfoFromDb = new LoanLimitInfo(new BigDecimal(100000));

        //when
        when(getMaxAmountAvailableLoan.get(loanCreate.getLoanType()))
                .thenReturn(Mono.just(loanLimitInfoFromDb));
        when(saveLoanPersistence.saveLoanRepository(loanCreate))
                .thenReturn(Mono.empty());

        //then
        createLoanService.createLoan(loanCreate)
                .as(StepVerifier::create)
                .verifyComplete();
    }
}
