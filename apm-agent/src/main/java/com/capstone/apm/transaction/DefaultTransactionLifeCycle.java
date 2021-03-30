package com.capstone.apm.transaction;

import com.capstone.apm.transaction.request.Request;
import com.capstone.apm.transaction.response.Response;

import static java.util.Objects.requireNonNull;

/*
* TransactionLifeCycle 의 구현체
 */
class DefaultTransactionLifeCycle implements TransactionLifeCycle {

    private final TransactionRepository transactionRepository;

    DefaultTransactionLifeCycle(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void startTransaction(Request request) {
        Transaction transaction = new Transaction();
        transactionRepository.addTransaction(transaction);
        transaction.start(request);
    }

    @Override
    public void endTransaction(Response response) {
        Transaction transaction = requireNonNull(transactionRepository.getTransaction());
        transaction.end(response);
        transactionRepository.removeTransaction();
    }
}
