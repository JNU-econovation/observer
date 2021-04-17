package com.capstone.apm.commons.event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Subscription;

public abstract class EventSubscriber<T extends Event<?>> implements Subscriber<T> {

    protected Subscription subscription;

    private final EventConfiguration configuration;

    private int requestNumber;

    public EventSubscriber(EventConfiguration configuration) {
        this.configuration = configuration;
        this.requestNumber = configuration.getEventRequestNumber();
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(requestNumber);
    }
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() { System.out.println("Subscriber Complete"); }
}
