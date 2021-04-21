package com.capstone.apm.agent;

import com.capstone.apm.collector.CollectorClient;

public class GracefulShutdownHook implements Runnable {

    private final CollectorClient collectorClient;

    public GracefulShutdownHook(){
        this.collectorClient = CollectorClient.getInstance();
    }

    @Override
    public void run() {
        collectorClient.sendDeadBit();
    }
}
