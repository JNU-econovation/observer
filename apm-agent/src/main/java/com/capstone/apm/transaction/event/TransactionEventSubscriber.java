package com.capstone.apm.transaction.event;

import com.capstone.apm.commons.event.Event;
import com.capstone.apm.commons.event.EventConfiguration;
import com.capstone.apm.commons.event.EventSubscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;


public class TransactionEventSubscriber extends EventSubscriber<TransactionEvent> {

    private final int REQUEST_EVENT_NUMBER = 1;
    private final ExecutorService executorService;

    public TransactionEventSubscriber(EventConfiguration eventConfiguration) {
        this.executorService = Executors.newFixedThreadPool(eventConfiguration.getThreadNum());
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(REQUEST_EVENT_NUMBER);
    }

    @Override
    public void onNext(TransactionEvent item) {
        executorService.submit(() -> {
            System.out.println("Thread ID : " + Thread.currentThread().getId() + " " +  item.getContent().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        subscription.request(REQUEST_EVENT_NUMBER);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Complete");
    }
}
