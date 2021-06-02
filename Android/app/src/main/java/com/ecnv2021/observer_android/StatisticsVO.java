package com.ecnv2021.observer_android;

import java.util.ArrayList;
import java.util.List;

public class StatisticsVO {
    private List<TransactionResponseDTO> successList = new ArrayList<>();
    private List<TransactionResponseDTO> failList = new ArrayList<>();
    private int successNum;
    private int failNum;

    public List<TransactionResponseDTO> getSuccessList() {
        return successList;
    }

    public void setSuccessList(ArrayList<TransactionResponseDTO> successList) {
        this.successList = successList;
    }

    public List<TransactionResponseDTO> getFailList() {
        return failList;
    }

    public void setFailList(ArrayList<TransactionResponseDTO> failList) {
        this.failList = failList;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

}

class TransactionResponseDTO {
    private long transactionStartTime;
    private long transactionTimeMillis;

    public long getTransactionStartTime() {
        return transactionStartTime;
    }

    public void setTransactionStartTime(int transactionStartTime) {
        this.transactionStartTime = transactionStartTime;
    }

    public long getTransactionTimeMillis() {
        return transactionTimeMillis;
    }

    public void setTransactionTimeMillis(int transactionTimeMillis) {
        this.transactionTimeMillis = transactionTimeMillis;
    }
}
