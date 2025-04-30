package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver;

import com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver.data.LoanData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends ReactiveCrudRepository<LoanData, Long> {
}
