package com.capstone.apm.transaction;

import com.capstone.apm.commons.util.RandomUtil;
import com.capstone.apm.commons.util.TimeUtil;
import com.capstone.apm.transaction.request.Request;
import com.capstone.apm.transaction.response.Response;

/*
* 트랜잭션 도중 서버에서 수집해야할 데이터
 */
class Transaction {
    private TransactionStatus transactionStatus;

    private String traceId;
    private String requestUri;
    private String clientAddr;

    private long startTransactionTime;
    private long endTransactionTime;
    private long threadId;

    private int statusCode;
    private int sequence;


    Transaction() { }

    public long getTransactionExecuteTime() {
        return TimeUtil.convertNanoToMilli(endTransactionTime - startTransactionTime);
    }

    public String getTraceId() {
        return traceId;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionStatus=" + transactionStatus +
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

    public void start(Request request) {
        this.startTransactionTime = System.nanoTime();
        this.threadId = Thread.currentThread().getId();
        this.traceId = getOrCreateTraceId(request);
        this.sequence = getOrCreateSequence(request);
        this.clientAddr = request.getRemoteAddr();
        this.requestUri = request.getRequestURI();
        this.transactionStatus = TransactionStatus.START;
    }

    public void end(Response response){
        this.statusCode = response.getStatusCode();
        this.endTransactionTime = System.nanoTime();
        this.transactionStatus = TransactionStatus.END;
    }

    private String getOrCreateTraceId(Request request){
        String traceId = request.getHeader("trace-id");
        if(traceId == null) {
            traceId = RandomUtil.getRandomTraceId();
        }
        return traceId;
    }

    private int getOrCreateSequence(Request request){
        String sequence = request.getHeader("sequence");
        int seq = 0;
        if(sequence != null)
            seq = Integer.parseInt(sequence) + 1;
        return seq;
    }

    enum TransactionStatus {
        START("Start"), END("End");

        String status;

        TransactionStatus(String status){
            this.status = status;
        }

        @Override
        public String toString() {
            return "TransactionStatus{" +
                    "status='" + status + '\'' +
                    '}';
        }
    }
}
