package com.capstone.apm.transaction.websocket;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;

public interface WebSocketFactory {
    WebSocketClient create(URI uri);
}
