package com.capstone.apm.transaction.websocket.connection;

import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.TransactionWebSocketClient;
import org.java_websocket.client.WebSocketClient;

import java.net.ConnectException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WebSocketService {

    private Queue<WebSocketClient> clients;
    private ServerConfiguration configuration;

    private int size;

    public WebSocketService(ServerConfiguration configuration, int size){
        clients = new LinkedList<>();
        this.size = size;
        this.configuration = configuration;
        createClients(size);
    }

    public WebSocketClient getClient() {
        WebSocketClient client = getOrCreateClient();
        try {
            if (!client.isOpen())
                client.connectBlocking();
            return client;
        }catch (InterruptedException | IllegalStateException ex) {
            ex.printStackTrace();
            throw new ServerConnectionFailedException(ex.getMessage());
        }
    }

    private WebSocketClient getOrCreateClient() {
        WebSocketClient client;
        if(!clients.isEmpty())
            client = clients.poll();
        else
            client = makeClient();
        return client;
    }

    public void offerClient(WebSocketClient client) {
        this.clients.offer(client);
    }

    private void createClients(int size) {
        for (int i = 0; i < size; i++) {
            TransactionWebSocketClient client = makeClient();
            clients.offer(client);
        }
    }

    private TransactionWebSocketClient makeClient() {
        TransactionWebSocketClient client = TransactionWebSocketClient.create(configuration.getUri());
        client.connect();
        return client;
    }
}
