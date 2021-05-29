package com.capstone.apm.transaction;

import com.capstone.apm.agent.Agent;

public class MysqlTransactionDto {

    private String dbHost;
    private String agentHost;
    private String serviceName;
    private String serverName;
    private String serviceType;

    private long transactionStartTime;
    private long transactionTimeMillis;
    
    public MysqlTransactionDto(String dbHost, String agentHost, String serviceName, long transactionStartTime, long transactionTimeMillis) {
        this.dbHost = dbHost;
        this.agentHost = agentHost;
        this.serviceName = serviceName;
        this.transactionStartTime = transactionStartTime;
        this.transactionTimeMillis = transactionTimeMillis;
        this.serverName = Agent.serverName;
        this.serviceType = "Database";
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

    public String getServerName() {
        return serverName;
    }

    public String getServiceType() {
        return serviceType;
    }
}
