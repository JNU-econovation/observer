package com.capstone.apm.transaction;


import com.capstone.apm.transaction.exception.TransactionNotFoundException;

import static java.util.Objects.*;

/*
* Thread별로 Transaction 데이터를 저장하는 저장소
 */
class TransactionRepository {

    private final ThreadLocal<Transaction> transactions;

    public TransactionRepository() {
        transactions = new ThreadLocal<>();
    }

    public void addTransaction(Transaction transaction){ transactions.set(transaction); }

    public Transaction getTransaction(){
        try {
            return requireNonNull(transactions.get());
        }catch (NullPointerException e){
            throw new TransactionNotFoundException(e.getMessage());
        }
    }

    public void removeTransaction() {
        transactions.remove();
    }

    public String getTransactionAsString() {
        return getTransaction().toString();
    }

    public String getTransactionTraceId() {
        return getTransaction().getTraceId();
    }

    public int getTransactionSequence() {
        return getTransaction().getSequence();
    }
}
