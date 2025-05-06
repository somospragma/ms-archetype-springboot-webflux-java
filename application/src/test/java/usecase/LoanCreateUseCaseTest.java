package usecase;

import com.mercantil.operationsandexecution.application.usecase.LoanCreateUseCase;
import com.mercantil.operationsandexecution.application.usecase.LoggerRegistryUseCase;
import com.mercantil.operationsandexecution.domain.ports.in.ICreateLoan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import util.ApplicationTestUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoanCreateUseCaseTest {

    @Mock
    ICreateLoan iCreateLoan;
    @Mock
    LoggerRegistryUseCase loggerRegistryUseCase;

    @InjectMocks
    LoanCreateUseCase loanCreateUseCase;

    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoanSuccess() {
        //given
        var loanCreate = ApplicationTestUtil.buildLoanCreate();

        //when
        when(iCreateLoan.createLoan(any())).thenReturn(Mono.empty());
        when(loggerRegistryUseCase.registryEvent(any(), any(), any()))
                .thenReturn(Mono.empty());

        //
        loanCreateUseCase.create(loanCreate)
                .as(StepVerifier::create)
                .verifyComplete();

    }
}
