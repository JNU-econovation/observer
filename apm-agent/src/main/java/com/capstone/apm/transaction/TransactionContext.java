package com.capstone.apm.transaction;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TransactionContext implements TransactionLifeCycle {

    private static final TransactionContext transactionContext = new TransactionContext();

    private final TransactionRepository transactionRepository;
    private final TransactionLifeCycle transactionLifeCycle;

    private TransactionContext() {
        /*
        * Dependency 생성 나중에 리팩터링하기
         */
        transactionRepository = new TransactionRepository();
        transactionLifeCycle = new DefaultTransactionLifeCycle(transactionRepository);
    }

    public static TransactionContext getTransactionContext() {
        return transactionContext;
    }

    @Override
    public void startTransaction(ServletRequest servletRequest) {
        transactionLifeCycle.startTransaction(servletRequest);
    }

    @Override
    public void endTransaction(ServletResponse servletResponse) {
        transactionLifeCycle.endTransaction(servletResponse);
    }


    public String getTransactionAsString() {
        return transactionRepository.getTransactionAsString();
    }
}
