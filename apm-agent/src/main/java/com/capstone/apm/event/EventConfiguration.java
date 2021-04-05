package com.capstone.apm.event;

public class EventConfiguration {

    private int threadNum;

    public EventConfiguration(int threadNum){
        this.threadNum = threadNum;
    }

    public int getThreadNum() {
        return threadNum;
    }
}
