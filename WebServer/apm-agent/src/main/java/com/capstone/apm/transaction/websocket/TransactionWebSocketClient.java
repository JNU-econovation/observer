package com.capstone.apm.transaction.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class TransactionWebSocketClient extends WebSocketClient {

    private TransactionWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public static TransactionWebSocketClient create(URI uri) {
        return new TransactionWebSocketClient(uri);
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        System.out.println("Collector Server Connected");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Server Response : " + message);
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Collector Server Closed");
        close();
    }

    @Override
    public void onError(Exception ex) {
        System.err.printf("Web Socket Error { Thread ID : %d } " + ex.getMessage() + " \n", Thread.currentThread().getId());
    }
}
