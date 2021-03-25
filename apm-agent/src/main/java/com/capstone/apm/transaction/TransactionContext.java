package com.capstone.apm.transaction;


import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.requireNonNull;

public class TransactionContext {

    private final ThreadLocal<Transaction> transactions;
    private static final TransactionContext transactionContext = new TransactionContext();

    private TransactionContext() {
        this.transactions = new ThreadLocal<>();
    }

    public static TransactionContext getTransactionContext() {
        return transactionContext;
    }

    public void startTransaction() {
        Transaction transaction = new Transaction();
        transactions.set(transaction);
    }

    public void startTransaction(String traceId) {
        Transaction transaction = new Transaction(requireNonNull(traceId));
        transactions.set(transaction);
    }

    public void endTransaction() {
        Transaction transaction = getTransaction();
        if(transaction != null) {
            transaction.endTransaction();
            this.transactions.remove();
        }
    }

    private Transaction getTransaction() {
        return transactions.get();
    }

    public void saveRequest(HttpServletRequest servletRequest) {
        getTransaction().saveRequest(servletRequest);
    }

    public String getTransactionAsString() {
        return getTransaction().toString();
    }
}
