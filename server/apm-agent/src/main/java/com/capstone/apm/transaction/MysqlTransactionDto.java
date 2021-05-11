package com.capstone.apm.transaction;

public class MysqlTransactionDto {
    
    private String host;
    private long transactionTimeMillis;
    
    public MysqlTransactionDto(String host, long transactionTimeMillis) {
        this.host = host;
        this.transactionTimeMillis = transactionTimeMillis;
    }
}
