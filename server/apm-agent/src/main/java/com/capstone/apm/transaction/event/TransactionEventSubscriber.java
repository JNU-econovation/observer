package com.capstone.apm.transaction.event;

import com.capstone.apm.collector.CollectorClient;
import com.capstone.apm.commons.event.EventConfiguration;
import com.capstone.apm.commons.event.EventSubscriber;
import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.connection.ServerConnectionFailedException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TransactionEventSubscriber extends EventSubscriber<TransactionEvent> {

    private final TransactionEventHandler eventHandler;

    public TransactionEventSubscriber(EventConfiguration eventConfiguration) {
        super(eventConfiguration);
        this.eventHandler = new TransactionEventHandler();
    }

    @Override
    public void onNext(TransactionEvent event) {
        eventHandler.handleEvent(event);
        subscription.request(1);
    }
}
