package com.capstone.apm.collector;

import com.capstone.apm.transaction.event.TransactionEvent;
import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.connection.WebSocketService;
import org.java_websocket.client.WebSocketClient;

public class CollectorClient {

    private final WebSocketService webSocketService;

    public CollectorClient(ServerConfiguration serverConfiguration) {
        this.webSocketService = new WebSocketService(serverConfiguration, new CollectorWebSocketFactory(), 5);
    }

    public void sendMessage(String message) {
        WebSocketClient client = webSocketService.getClient();
        client.send(message);
        webSocketService.offerClient(client);
    }

}
