package com.capstone.apm.transaction;

import com.capstone.apm.agent.Agent;

public class TransactionDto {

    private TransactionDto(){}

    private String transactionStatus;

    private String traceId;
    private String requestUri;
    private String clientAddr;
    private String serverName;
    private String serviceName;
    private String serviceType;

    private long transactionTimeMillis;

    private long threadId;

    private int statusCode;
    private int sequence;

    public static TransactionDto of(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.transactionStatus = transaction.getStatusAsString();

        transactionDto.traceId = transaction.getTraceId();
        transactionDto.requestUri = transaction.getRequestUri();
        transactionDto.clientAddr = transaction.getClientAddr();

        transactionDto.transactionTimeMillis = transaction.getEndTransactionTime() - transaction.getStartTransactionTime();
        transactionDto.threadId = transaction.getThreadId();
        transactionDto.statusCode = transaction.getStatusCode();
        transactionDto.sequence = transaction.getSequence();

        transactionDto.serviceName = Agent.SERVICE_NAME;
        transactionDto.serverName = Agent.serverName + ":" + transaction.getServerPort();
        transactionDto.serviceType = "Service";

        return transactionDto;
    }
}
