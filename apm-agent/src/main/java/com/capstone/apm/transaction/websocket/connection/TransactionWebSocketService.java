package com.capstone.apm.transaction.websocket.connection;

import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.TransactionWebSocketClient;
import org.java_websocket.client.WebSocketClient;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransactionWebSocketService {
    private BlockingQueue<WebSocketClient> clients;
    private ServerConfiguration configuration;

    private int maxPoolSize;

    public TransactionWebSocketService(ServerConfiguration configuration, int maxPoolSize){
        this.clients = new LinkedBlockingQueue<>(maxPoolSize);
        this.maxPoolSize = maxPoolSize;
        this.configuration = configuration;

        createClients();
    }

    public WebSocketClient getClient() {
        try {
            WebSocketClient client = clients.take();
            if(!client.isOpen())
                client.connectBlocking();
            return client;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void offerClient(WebSocketClient client) {
        this.clients.offer(client);
    }

    private void createClients(){
        System.out.println("Create WebSocket Client : " + maxPoolSize);
        for(int i = 0; i < maxPoolSize; i++) {
            TransactionWebSocketClient client = TransactionWebSocketClient.create(configuration.getUri());
            clients.offer(client);
            client.connect();
        }
    }
}
