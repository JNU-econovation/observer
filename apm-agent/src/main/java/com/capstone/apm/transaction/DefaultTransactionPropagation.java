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
    public void injectTraceId(Map<String, List<String>> headers) {
        String traceId = requireNonNull(transactionRepository.getTransactionTraceId());
        System.out.println("Inject Trace Id : " + traceId);
        headers.put("trace-id", List.of(traceId));
    }
}
