package com.mercantil.operationsandexecution.infrastructure.adapter.sqlserver;

import com.mercantil.operationsandexecution.infrastructure.adapter.sqlserver.data.LoanData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends ReactiveCrudRepository<LoanData, Long> {
}
