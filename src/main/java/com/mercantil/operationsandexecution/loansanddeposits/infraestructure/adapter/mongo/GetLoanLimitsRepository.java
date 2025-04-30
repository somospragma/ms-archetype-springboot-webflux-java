package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo;

import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.mongo.entity.LoanLimitsData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface GetLoanLimitsRepository extends ReactiveMongoRepository<LoanLimitsData, String> {

    Mono<LoanLimitsData> findByLoanType(String loanType);
}
