package com.capstone.apm.transaction;

import com.capstone.apm.commons.util.RandomUtil;
import com.capstone.apm.commons.util.TimeUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Transaction {
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

    public void endTransaction() {
        this.endTransactionTime = System.nanoTime();
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

    public void saveRequest(HttpServletRequest servletRequest) {
        this.clientAddr = servletRequest.getRemoteAddr();
        this.requestUri = servletRequest.getRequestURI();
    }
}
