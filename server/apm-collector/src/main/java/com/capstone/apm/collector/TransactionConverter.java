package com.capstone.apm.collector;


import com.capstone.apm.collector.node.Transaction;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements Converter<JSONObject, Transaction> {
    @Override
    public Transaction convert(JSONObject source) {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(source.getString("transactionStatus"));
        transaction.setTransactionStartTime(source.getLong("transactionStartTime"));
        transaction.setTransactionTimeMillis(source.getLong("transactionTimeMillis"));
        transaction.setRemoteServerType("Service");
        transaction.setRemoteAddr(source.getString("serverName"));
        transaction.setClientAddr(source.getString("clientAddr"));
        transaction.setStatusCode(source.getInt("statusCode"));
        transaction.setTraceId(source.getString("traceId"));
        return transaction;
    }
}
