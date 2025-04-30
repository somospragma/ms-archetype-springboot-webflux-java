package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo;

import com.mercantil.operationsandexecution.loansanddeposits.domain.models.LoanLimitInfo;
import com.mercantil.operationsandexecution.loansanddeposits.domain.ports.out.GetMaxAmountAvailableLoan;
import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo.entity.LoanLimitsData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo.mapper.LoanLimitMapper.toModel;

@Component
@Log4j2
@RequiredArgsConstructor
public class GetLoanLimits implements GetMaxAmountAvailableLoan {

    private final GetLoanLimitsRepository getLoanLimitsRepository;
    @Override
    public Mono<LoanLimitInfo> get(String loanType) {
        LoanLimitsData dataExample = new LoanLimitsData();
        dataExample.setLoanType(loanType);

        return getLoanLimitsRepository.findByLoanType(loanType)
                .flatMap(data -> Mono.just(toModel(data)));
    }
}
