package com.capstone.apm.transaction;

import com.capstone.apm.commons.util.RandomUtil;
import com.capstone.apm.commons.util.TimeUtil;
import com.capstone.apm.transaction.request.Request;
import com.capstone.apm.transaction.response.Response;

/*
* 트랜잭션 도중 서버에서 수집해야할 데이터
 */
class Transaction {
    private String traceId;
    private String requestUri;
    private String clientAddr;

    private long startTransactionTime;
    private long endTransactionTime;
    private long threadId;

    Transaction() {
        this.startTransactionTime = System.nanoTime();
        this.threadId = Thread.currentThread().getId();
        this.traceId = RandomUtil.getRandomTraceId();
    }

    public long getTransactionExecuteTime() {
        return TimeUtil.convertNanoToMilli(endTransactionTime - startTransactionTime);
    }

    public String getTraceId() {
        return traceId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "startTransactionTime=" + startTransactionTime +
                ", endTransactionTime=" + endTransactionTime +
                ", traceId='" + traceId + '\'' +
                ", threadId='" + threadId + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", clientAddr='" + clientAddr + '\'' +
                '}';
    }

    public void start(Request request) {
        this.traceId = getOrCreateTraceId(request);
        this.clientAddr = request.getRemoteAddr();
        this.requestUri = request.getRequestURI();
    }

    public void end(Response response){
        this.endTransactionTime = System.nanoTime();
    }

    private String getOrCreateTraceId(Request request){
        String traceId = request.getHeader("trace-id");
        if(traceId == null)
            traceId = RandomUtil.getRandomTraceId();
        return traceId;
    }
}
