package com.capstone.apm.util;

import com.capstone.apm.transaction.MysqlTransactionDto;
import com.google.gson.Gson;

public class MySqlTransactionSerializer {
    
    private static Gson gson;
    
    static {
        gson = new Gson();
    }
    
    public static String serialize(MysqlTransactionDto mysqlTransactionDto) {
        return gson.toJson(mysqlTransactionDto);
    }
}
