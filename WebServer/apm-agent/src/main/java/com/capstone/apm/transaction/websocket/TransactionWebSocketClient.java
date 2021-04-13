package com.capstone.apm.transaction.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;

public class TransactionWebSocketClient extends WebSocketClient {

    private TransactionWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public static TransactionWebSocketClient create(URI uri) {
        return new TransactionWebSocketClient(uri);
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        //Collector Server Connected
        System.out.println("Collector Server Connected");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Server Response : " + message);
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        //TODO Server Connection이 끊겼을때 처리
        System.out.println("Server Connection Closed : " + reason);
    }

    @Override
    public void onError(Exception ex) {
        //TODO Server Connection Error 처리
        System.out.println("Collector Server Error : " + ex.getMessage());
    }
}
