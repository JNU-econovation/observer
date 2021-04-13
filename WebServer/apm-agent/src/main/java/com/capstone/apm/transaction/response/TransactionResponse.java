package com.capstone.apm.transaction.response;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionResponse implements Response {
    private int statusCode;
    private Map<String, String> headers;

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getHeader(String headerName) {
        return headers.get(headerName);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }


    public static TransactionResponse ofServletResponse(HttpServletResponse response){
        TransactionResponse transactionResponse = new TransactionResponse();

        Collection<String> headerNames = response.getHeaderNames();
        Map<String, String> headers =
                headerNames.stream().collect(
                        Collectors.toMap(
                                headerName -> headerName,
                                response::getHeader,
                                (oldValue, newValue) -> String.join(",", oldValue, newValue)));

        transactionResponse.headers = headers;
        transactionResponse.statusCode = response.getStatus();

        return transactionResponse;
    }
}
