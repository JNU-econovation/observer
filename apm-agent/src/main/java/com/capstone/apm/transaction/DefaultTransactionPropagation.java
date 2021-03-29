package com.capstone.apm.transaction;

import java.util.List;
import java.util.Map;

import static java.util.Objects.*;

/*
* TransactionPropagation 의 구현체
 */
class DefaultTransactionPropagation implements TransactionPropagation{

    private final TransactionRepository transactionRepository;

    public DefaultTransactionPropagation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void modifyHeader(Map<String, List<String>> headers) {
        String traceId = requireNonNull(transactionRepository.getTransactionTraceId());
        headers.put("Trace-Id", List.of(traceId));
    }
}
