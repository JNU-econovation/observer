package com.capstone.apm.transaction;

public class MysqlTransactionDto {
    
    private String dbHost;
    private String agentHost;
    private String serviceName;
    private long transactionTimeMillis;
    
    public MysqlTransactionDto(String dbHost, String agentHost, String serviceName, long transactionTimeMillis) {
        this.dbHost = dbHost;
        this.agentHost = agentHost;
        this.serviceName = serviceName;
        this.transactionTimeMillis = transactionTimeMillis;
    }
    
    public String getDbHost() {
        return dbHost;
    }
    
    public String getAgentHost() {
        return agentHost;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public long getTransactionTimeMillis() {
        return transactionTimeMillis;
    }
}
