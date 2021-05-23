package com.capstone.apm.transaction.request;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class TransactionRequest implements Request {
    private String remoteAddr;
    private String requestUri;
    private String serverName;
    private String serverPort;
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

    @Override
    public String getServerName() {
        return serverName;
    }

    @Override
    public String getServerPort() {
        return serverPort;
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

         transactionRequest.headers = headers;
         transactionRequest.serverName = request.getServerName();
         transactionRequest.requestUri = request.getRequestURI();
         transactionRequest.remoteAddr = request.getRemoteAddr();
         transactionRequest.serverPort = request.getServerPort() + "";
         return transactionRequest;
    }
}
