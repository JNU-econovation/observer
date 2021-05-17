package com.capstone.apm.transformer;

import com.capstone.apm.collector.CollectorClient;
import com.capstone.apm.transaction.MysqlTransactionDto;
import com.capstone.apm.transformer.interceptor.Interceptor;
import com.capstone.apm.util.Serializer;
import com.capstone.apm.util.UrlParser;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import net.bytebuddy.asm.Advice;

import java.sql.Connection;
import java.sql.SQLException;

// executeQuery method is used when Hibernate execute select query
public class HibernateSelectTransformer extends AbstractTransformer {
    
    private final String EXPECTED_CLASS_NAME = "com.mysql.cj.jdbc.ClientPreparedStatement";
    private final String EXPECTED_METHOD_NAME = "executeQuery";
    private static final String SERVICE_NAME = "MySQL";
    private static final String AGENT_HOST = "localhost:8080";
    
    @Override
    protected String getExpectedMethodName() {
        return EXPECTED_METHOD_NAME;
    }
    
    @Override
    protected String getExpectedClassName() {
        return EXPECTED_CLASS_NAME;
    }
    
    @Override
    protected Class<? extends Interceptor> getInterceptor() {
        return HibernateSelectTransformer.MySqlInterceptor.class;
    }
    
    static class MySqlInterceptor implements Interceptor {
        
        @Advice.OnMethodEnter
        private static long enter() {
            return System.currentTimeMillis();
        }
        
        @Advice.OnMethodExit
        private static void exit(
                @Advice.Enter long transactionEnterTime,
                @Advice.This Object statement) throws SQLException {
            CollectorClient collectorClient = CollectorClient.getInstance();
            ClientPreparedStatement clientPreparedStatement = (ClientPreparedStatement) statement;
            Connection connection = clientPreparedStatement.getConnection();
            String url = connection.getMetaData().getURL();
            String dbHost = UrlParser.parseToHost(url);
            long transactionTime = System.currentTimeMillis() - transactionEnterTime;
            MysqlTransactionDto mysqlTransactionDto = new MysqlTransactionDto(dbHost, AGENT_HOST, SERVICE_NAME, transactionTime);
            collectorClient.sendMessage(Serializer.serialize(mysqlTransactionDto));
        }
    }
}
