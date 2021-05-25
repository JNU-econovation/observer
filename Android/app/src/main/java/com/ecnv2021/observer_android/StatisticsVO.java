package com.ecnv2021.observer_android;

import java.util.ArrayList;

public class StatisticsVO {
    private ArrayList<Values> successList = new ArrayList<>();
    private ArrayList<Values> failList = new ArrayList<>();
    private int successNum;
    private int failNum;

    public ArrayList<Values> getSuccessList() {
        return successList;
    }

    public void setSuccessList(ArrayList<Values> successList) {
        this.successList = successList;
    }

    public ArrayList<Values> getFailList() {
        return failList;
    }

    public void setFailList(ArrayList<Values> failList) {
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
class Values{
    private float xValue;
    private float yValue;

    public float getxValue() {
        return xValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }
}
