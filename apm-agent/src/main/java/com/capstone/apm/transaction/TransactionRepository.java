package com.capstone.apm.transaction;

/*
* Thread별로 Transaction 데이터를 저장하는 저장소
 */
class TransactionRepository {

    private final ThreadLocal<Transaction> transactions;

    public TransactionRepository() {
        transactions = new ThreadLocal<>();
    }

    public void addTransaction(Transaction transaction){
        transactions.set(transaction);
    }

    public Transaction getTransaction(){
        return transactions.get();
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
}
