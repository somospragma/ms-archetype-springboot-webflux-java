package com.mercantil.operationsandexecution.infrastructure;

import com.mercantil.operationsandexecution.infrastructure.data.LoanData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends ReactiveCrudRepository<LoanData, Long> {
}
