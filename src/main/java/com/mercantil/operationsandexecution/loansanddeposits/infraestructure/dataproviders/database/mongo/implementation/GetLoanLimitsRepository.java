package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.dataproviders.database.mongo.implementation;

import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.dataproviders.database.mongo.LoanLimitsData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface GetLoanLimitsRepository extends ReactiveMongoRepository<LoanLimitsData, String> {

    Mono<LoanLimitsData> findByLoanType(String loanType);
}
