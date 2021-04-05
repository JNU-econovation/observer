package com.capstone.apm.transaction;

public class TransactionDto {

    private TransactionDto(){}

    private String transactionStatus;

    private String traceId;
    private String requestUri;
    private String clientAddr;

    private long startTransactionTime;
    private long endTransactionTime;
    private long threadId;

    private int statusCode;
    private int sequence;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "transactionStatus='" + transactionStatus + '\'' +
                ", traceId='" + traceId + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", clientAddr='" + clientAddr + '\'' +
                ", startTransactionTime=" + startTransactionTime +
                ", endTransactionTime=" + endTransactionTime +
                ", threadId=" + threadId +
                ", statusCode=" + statusCode +
                ", sequence=" + sequence +
                '}';
    }

    public static TransactionDto of(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.transactionStatus = transaction.getStatusAsString();

        transactionDto.traceId = transaction.getTraceId();
        transactionDto.requestUri = transaction.getRequestUri();
        transactionDto.clientAddr = transaction.getClientAddr();

        transactionDto.startTransactionTime = transaction.getStartTransactionTime();
        transactionDto.endTransactionTime = transaction.getEndTransactionTime();
        transactionDto.threadId = transaction.getThreadId();

        transactionDto.statusCode = transaction.getStatusCode();
        transactionDto.sequence = transaction.getSequence();

        return transactionDto;
    }
}
