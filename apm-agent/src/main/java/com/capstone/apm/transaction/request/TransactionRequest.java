package com.capstone.apm.transaction.request;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class TransactionRequest implements Request {
    private String remoteAddr;
    private String requestUri;
    private Map<String, String> headers;

    @Override
    public String getRemoteAddr() {
        return remoteAddr;
    }

    @Override
    public String getRequestURI() {
        return requestUri;
    }

    @Override
    public String getHeader(String headerName) {
        return headers.get(headerName);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }


    public static TransactionRequest ofHttpServletRequest(HttpServletRequest request){
         TransactionRequest transactionRequest = new TransactionRequest();

         Map<String, String> headers = new HashMap<>();
         Enumeration<String> headerNames = request.getHeaderNames();
         while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValue = request.getHeaders(headerName);
            headers.put(headerName, headerValue.nextElement());
         }

         headers.forEach((key, value) -> {
             System.out.println("Key : " + key + ", Value : " + value);
         });

         transactionRequest.headers = headers;
         transactionRequest.requestUri = request.getRequestURI();
         transactionRequest.remoteAddr = request.getRemoteAddr();

         return transactionRequest;
    }
}
