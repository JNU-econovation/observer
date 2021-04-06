package com.capstone.apm.transaction.event;

import com.capstone.apm.commons.event.Event;
import com.capstone.apm.commons.event.EventSubscriber;

import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;


public class TransactionEventSubscriber extends EventSubscriber<TransactionEvent> {

    private final int REQUEST_EVENT_NUMBER = 1;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(REQUEST_EVENT_NUMBER);
    }

    @Override
    public void onNext(TransactionEvent item) {
        System.out.println("Thread ID : " +
                Thread.currentThread().getId() + " Transaction Event : " + item.getContent());
        subscription.request(REQUEST_EVENT_NUMBER);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
