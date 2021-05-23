package com.capstone.apm.collector;

import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transaction.websocket.connection.WebSocketService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CollectorClient {

    private static CollectorClient collectorClient;

    private final WebSocketService webSocketService;
    private final OkHttpClient okHttpClient;
    private final ServerConfiguration configuration;

    private final String BIRTH_BIT_URI = "/birth";
    private final String DEAD_BIT_URI = "/dead";
    private final String HEART_BIT_URI = "/heart";

    private boolean isServerConnected;

    private CollectorClient(ServerConfiguration serverConfiguration) {
        this.configuration = serverConfiguration;
        this.webSocketService = new WebSocketService(serverConfiguration, new CollectorWebSocketFactory(), 1);
        this.okHttpClient = new OkHttpClient();
        isServerConnected = false;
    }

    public static void initialize(ServerConfiguration serverConfiguration) {
        collectorClient = new CollectorClient(serverConfiguration);
        collectorClient.sendBirthBit();
    }

    public static CollectorClient getInstance() {
        return collectorClient;
    }

    public void sendMessage(String message) {
        if(!isServerConnected) {
            System.err.println("Server Not Connected");
            sendBirthBit();
        }

        WebSocketClient client = webSocketService.getClient();
        client.send(message);
        webSocketService.offerClient(client);
    }

    public void sendBirthBit() {
        try {
            Request request = getPostRequest(configuration.getUri().toString() + BIRTH_BIT_URI, "");
            Response response = okHttpClient.newCall(request).execute();
            isServerConnected = true;
            System.out.println(response.body().string());
        } catch (IOException e) {
            isServerConnected = false;
            e.printStackTrace();
        }
    }

    public void sendDeadBit() {
        try {
            Request request = getPostRequest(configuration.getUri().toString() + DEAD_BIT_URI, "");
            Response response =
                    okHttpClient.newCall(request).execute();
            isServerConnected = false;
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendHeartBit() {
        try {
            Request request = getPostRequest(configuration.getUri().toString() + HEART_BIT_URI, "");
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Request getPostRequest(String url, String body) {
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(body.getBytes(StandardCharsets.UTF_8)))
                .build();
    }

}
