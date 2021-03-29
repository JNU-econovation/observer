package com.capstone.apm.transaction;

import com.capstone.apm.commons.util.RandomUtil;
import com.capstone.apm.commons.util.TimeUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Transaction {
    private long startTransactionTime;
    private long endTransactionTime;
    private long threadId;
    private String traceId;
    private String requestUri;
    private String clientAddr;

    Transaction() {
        this.startTransactionTime = System.nanoTime();
        this.threadId = Thread.currentThread().getId();
        this.traceId = RandomUtil.getRandomTraceId();
    }

    Transaction(String traceId) {
        this.startTransactionTime = System.nanoTime();
        this.threadId = Thread.currentThread().getId();
        this.traceId = traceId;
    }

    public long getTransactionExecuteTime() {
        return TimeUtil.convertNanoToMilli(endTransactionTime - startTransactionTime);
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

    public void start(ServletRequest servletRequest) {
        this.clientAddr = servletRequest.getRemoteAddr();

        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            String traceId = getOrCreateTraceId(httpServletRequest);

            this.requestUri = httpServletRequest.getRequestURI();
            this.traceId = traceId;
        }
    }

    public void end(ServletResponse servletResponse){
        this.endTransactionTime = System.nanoTime();
        if(servletResponse instanceof HttpServletResponse){
            HttpServletResponse response = (HttpServletResponse) servletResponse;

        }
    }

    private String getOrCreateTraceId(HttpServletRequest servletRequest){
        String header = servletRequest.getHeader("Trace-Id");
        if(header == null)
            header = RandomUtil.getRandomTraceId();
        return header;
    }
}
