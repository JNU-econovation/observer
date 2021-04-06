package com.capstone.apm.commons.event;

public class EventConfiguration {

    private int threadNum;
    private int maxEventBufferSize;

    public EventConfiguration(int threadNum, int maxEventBufferSize){
        this.threadNum = threadNum;
        this.maxEventBufferSize = maxEventBufferSize;
    }

    public int getThreadNum() {
        return threadNum;
    }
    public int getMaxEventBufferSize() {
        return maxEventBufferSize;
    }
}
