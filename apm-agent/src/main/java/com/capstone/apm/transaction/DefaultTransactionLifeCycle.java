package com.capstone.apm.transaction;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

class DefaultTransactionLifeCycle implements TransactionLifeCycle{

    private final TransactionRepository transactionRepository;

    DefaultTransactionLifeCycle(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void startTransaction(ServletRequest servletRequest) {
        Transaction transaction = new Transaction();
        transactionRepository.addTransaction(transaction);
        transaction.start(servletRequest);
    }

    @Override
    public void endTransaction(ServletResponse servletResponse) {
        Transaction transaction = transactionRepository.getTransaction();
        if(transaction != null) {
            transaction.end(servletResponse);
            transactionRepository.removeTransaction();
        }
    }
}
