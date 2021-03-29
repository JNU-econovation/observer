package com.capstone.apm.transaction;

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
}
