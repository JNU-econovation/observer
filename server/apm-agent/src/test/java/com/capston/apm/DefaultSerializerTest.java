package com.capston.apm;

import com.capstone.apm.transaction.MysqlTransactionDto;
import com.capstone.apm.util.Serializer;
import org.junit.jupiter.api.Test;


class DefaultSerializerTest {
    
    private static final String DB_HOST = "www.naver.com";
    private static final long TRANSACTION_TIME_MILLIS = 1000l;
    private static final String AGENT_HOST = "localhost:8080";
    private static final String SERVICE_NAME = "MySQL";

    @Test
    public void serializeTest() {
        MysqlTransactionDto mysqlTransactionDto = new MysqlTransactionDto(DB_HOST, AGENT_HOST, SERVICE_NAME, TRANSACTION_TIME_MILLIS);
        String actualJsonData = Serializer.serialize(mysqlTransactionDto);
        System.out.println(actualJsonData);
    }

    
}