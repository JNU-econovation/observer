package com.capston.apm;

import com.capstone.apm.transaction.MysqlTransactionDto;
import com.capstone.apm.util.MySqlTransactionSerializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlTransactionSerializerTest {
    
    private static final String HOST = "www.naver.com";
    private static final long TRANSACTION_TIME_MILLIS = 1000l;

    @Test
    public void serializeTest() {
        String expectedJsonData = "{" +
                "\"host\":\"" + HOST + "\"," +
                "\"transactionTimeMillis\":" + TRANSACTION_TIME_MILLIS + "}";
        
        MysqlTransactionDto mysqlTransactionDto =
                new MysqlTransactionDto(HOST, TRANSACTION_TIME_MILLIS);
        String actualJsonData = MySqlTransactionSerializer.serialize(mysqlTransactionDto);
        assertEquals(expectedJsonData, actualJsonData);
    }

    
}