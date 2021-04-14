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
        client = checkClientIsClosed(client);
        checkClientIsConnected(client);
        return client;
    }

    private WebSocketClient getOrCreateClient() {
        if(!clients.isEmpty())
            return clients.poll();
        else
            return makeClient();
    }

    private WebSocketClient checkClientIsClosed(WebSocketClient client) {
        if(client.isClosed())
            return makeClient();
        return client;
    }

    private void checkClientIsConnected(WebSocketClient client) {
        try {
            if(!client.isOpen())
                client.connectBlocking();
        }catch (IllegalStateException | InterruptedException ex) {
            throw new ServerConnectionFailedException(ex.getMessage());
        }
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
        return TransactionWebSocketClient.create(configuration.getUri());
    }
}
