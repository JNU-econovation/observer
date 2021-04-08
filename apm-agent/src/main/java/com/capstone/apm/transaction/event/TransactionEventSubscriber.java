package com.capstone.apm.transaction.event;

import com.capstone.apm.commons.event.Event;
import com.capstone.apm.commons.event.EventConfiguration;
import com.capstone.apm.commons.event.EventSubscriber;
import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.TransactionWebSocketClient;
import com.capstone.apm.transaction.websocket.connection.TransactionWebSocketService;
import org.java_websocket.client.WebSocketClient;

import java.net.ConnectException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.Flow.*;


public class TransactionEventSubscriber extends EventSubscriber<TransactionEvent> {

    private final ExecutorService executorService;
    private final TransactionWebSocketService webSocketService;

    private final int DEFAULT_CONNECTION_POOL_SIZE = 5;

    public TransactionEventSubscriber(ServerConfiguration serverConfiguration,
                                      EventConfiguration eventConfiguration,
                                      int threadPoolSize, int connectionPoolSize) {
        super(eventConfiguration);
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.webSocketService = new TransactionWebSocketService(serverConfiguration, connectionPoolSize);
    }

    public TransactionEventSubscriber(ServerConfiguration serverConfiguration,
                                      EventConfiguration eventConfiguration) {
        super(eventConfiguration);
        this.executorService = Executors.newCachedThreadPool();
        this.webSocketService = new TransactionWebSocketService(serverConfiguration, DEFAULT_CONNECTION_POOL_SIZE);
    }

    @Override
    public void onNext(TransactionEvent item) {
        executorService.execute(() -> {
            WebSocketClient client = webSocketService.getClient();
            if(client != null) {
                client.send(item.getContent().toString());
                webSocketService.offerClient(client);
            }
        });
        subscription.request(1);
    }
}
