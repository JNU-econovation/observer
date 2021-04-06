package com.capstone.apm.commons.event;


import java.util.concurrent.*;
import java.util.function.BiConsumer;

public class EventPublisher<T extends Event<?>> extends SubmissionPublisher<T> {

    public EventPublisher(EventConfiguration eventConfiguration){
        super(Executors.newFixedThreadPool(eventConfiguration.getThreadNum()),
                eventConfiguration.getMaxEventBufferSize());
    }

}
