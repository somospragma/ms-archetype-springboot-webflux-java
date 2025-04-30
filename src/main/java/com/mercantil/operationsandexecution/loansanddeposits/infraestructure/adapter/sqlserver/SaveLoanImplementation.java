package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanCreate;
import com.mercantil.operationsandexecution.loansanddeposits.domain.ports.out.SaveLoanPersistence;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver.mapper.LoanDataMapper.toData;

@Component
@RequiredArgsConstructor
public class SaveLoanImplementation implements SaveLoanPersistence {

    private final LoanRepository loanRepository;
    private static final Logger logger = LoggerFactory.getLogger(SaveLoanImplementation.class);
    @Override
    public Mono<Void> saveLoanRepository(LoanCreate loanCreate) {
        logger.info("Se almacena pretamos.------------------------");
        return loanRepository.save(toData(loanCreate))
                .then();
    }
}
