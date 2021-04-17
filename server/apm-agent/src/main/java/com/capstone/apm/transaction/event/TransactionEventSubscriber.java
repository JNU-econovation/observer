package com.capstone.apm.transaction.event;

import com.capstone.apm.commons.event.EventConfiguration;
import com.capstone.apm.commons.event.EventSubscriber;
import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.connection.ServerConnectionFailedException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TransactionEventSubscriber extends EventSubscriber<TransactionEvent> {

    private final ExecutorService executorService;
    private final TransactionEventHandler eventHandler;

    public TransactionEventSubscriber(ServerConfiguration serverConfiguration,
                                      EventConfiguration eventConfiguration,
                                      int threadPoolSize, int initialSocketSize) {
        super(eventConfiguration);
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.eventHandler = new TransactionEventHandler(serverConfiguration, initialSocketSize);
    }

    @Override
    public void onNext(TransactionEvent event) {
        eventHandler.handleEvent(event);
        subscription.request(1);
    }
}
