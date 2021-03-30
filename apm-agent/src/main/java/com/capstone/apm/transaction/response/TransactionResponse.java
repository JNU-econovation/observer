package com.capstone.apm.transaction.response;

import javax.servlet.http.HttpServletResponse;

public class TransactionResponse implements Response{
    private int statusCode;

    @Override
    public int getStatusCode() {
        return statusCode;
    }


    public static TransactionResponse ofServletResponse(HttpServletResponse response){
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.statusCode = response.getStatus();
        return transactionResponse;
    }
}
