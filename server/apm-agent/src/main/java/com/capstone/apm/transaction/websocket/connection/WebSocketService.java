package com.capstone.apm.transaction.websocket.connection;

import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.WebSocketFactory;
import org.java_websocket.client.WebSocketClient;

import java.util.LinkedList;
import java.util.Queue;

public class WebSocketService {

    private Queue<WebSocketClient> clients;
    private ServerConfiguration configuration;
    private WebSocketFactory factory;

    private int socketNum;

    public WebSocketService(ServerConfiguration configuration, WebSocketFactory factory, int socketNum){
        clients = new LinkedList<>();
        this.socketNum = socketNum;
        this.configuration = configuration;
        this.factory = factory;
        createClients(socketNum);
    }

    public synchronized WebSocketClient getClient() {
        WebSocketClient client = getOrCreateClient();
        client = checkClientIsClosed(client);
        checkClientIsConnected(client);
        return client;
    }

    public synchronized void offerClient(WebSocketClient client) {
        this.clients.offer(client);
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


    private void createClients(int size) {
        for (int i = 0; i < size; i++) {
            WebSocketClient client = makeClient();
            clients.offer(client);
        }
    }

    private WebSocketClient makeClient() {
        return factory.create(configuration.getUri());
    }
}
