package com.capstone.apm.collector;

import com.capstone.apm.collector.node.Node;
import com.capstone.apm.collector.node.NodeService;
import com.capstone.apm.collector.node.Transaction;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
@Component
public class TransactionHandler extends TextWebSocketHandler {

    private final TransactionConverter transactionConverter;
    private final DatabaseConverter databaseConverter;
    private final NodeService nodeService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);

        String serviceType = jsonObject.getString("serviceType");
        Transaction transaction;
        if(serviceType.equals("Service"))
            transaction = transactionConverter.convert(jsonObject);
        else if(serviceType.equals("Database"))
            transaction = databaseConverter.convert(jsonObject);
        else
            throw new IllegalStateException("Can not Reached Here");
        System.out.println(payload);
        nodeService.save(transaction, jsonObject.getString("serviceName"), jsonObject.getString("serverName"));
    }
}
