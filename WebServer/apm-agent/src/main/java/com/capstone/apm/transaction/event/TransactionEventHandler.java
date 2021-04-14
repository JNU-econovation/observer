package com.capstone.apm.transaction.event;

import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.connection.ServerConnectionFailedException;
import com.capstone.apm.transaction.websocket.connection.WebSocketService;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public class TransactionEventHandler {

    private final WebSocketService webSocketService;

    public TransactionEventHandler(ServerConfiguration configuration, int initialSocketSize) {
        this.webSocketService = new WebSocketService(configuration, initialSocketSize);
    }

    public void handleEvent(TransactionEvent event) throws ServerConnectionFailedException {
        try {
            WebSocketClient client = webSocketService.getClient();
            client.send(event.getContent().toString());
            webSocketService.offerClient(client);
        }catch (ServerConnectionFailedException | WebsocketNotConnectedException e){
            System.err.println("Server Connection Failed. So Event is Ignored : " + e);
        }
    }
}
