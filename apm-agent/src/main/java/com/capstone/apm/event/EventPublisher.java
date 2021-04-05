package com.capstone.apm.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventPublisher {

    private final ExecutorService executorService;

    public EventPublisher(EventConfiguration eventConfiguration){
        executorService = Executors.newFixedThreadPool(eventConfiguration.getThreadNum());
    }

    public void publishEvent(Event<?> event){
        executorService.submit(event);
    }
}
