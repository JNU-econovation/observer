package com.capstone.apm.collector;

import com.capstone.apm.transaction.websocket.WebSocketFactory;
import org.java_websocket.client.WebSocketClient;

import java.net.URI;

public class CollectorWebSocketFactory implements WebSocketFactory {

    @Override
    public WebSocketClient create(URI uri) {
        return CollectorWebSocketClient.create(uri);
    }
}
