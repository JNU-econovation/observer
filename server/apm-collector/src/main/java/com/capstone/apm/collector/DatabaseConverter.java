package com.capstone.apm.collector;

import com.capstone.apm.collector.node.Transaction;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConverter implements Converter<JSONObject, Transaction> {
    @Override
    public Transaction convert(JSONObject source) {
        Transaction transaction = new Transaction();
        transaction.setTransactionStartTime(source.getLong("transactionStartTime"));
        transaction.setTransactionTimeMillis(source.getLong("transactionTimeMillis"));
        transaction.setRemoteAddr(source.getString("dbHost"));
        transaction.setClientAddr(source.getString("agentHost"));
        transaction.setRemoteServerType("Database");
        return transaction;
    }
}
