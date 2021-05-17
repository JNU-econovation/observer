package com.capstone.apm.transaction.event;

import com.capstone.apm.collector.CollectorClient;
import com.capstone.apm.transaction.websocket.connection.ServerConnectionFailedException;
import com.capstone.apm.util.Serializer;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public class TransactionEventHandler {
    
    private final CollectorClient collectorClient;
    
    public TransactionEventHandler() {
        this.collectorClient = CollectorClient.getInstance();
    }
    
    public void handleEvent(TransactionEvent event) {
        try {
            collectorClient.sendMessage(Serializer.serialize(event.getContent()));
        } catch (ServerConnectionFailedException | WebsocketNotConnectedException e) {
            System.err.println("Server Connection Failed. So Event is Ignored : " + e);
        }
    }
}
