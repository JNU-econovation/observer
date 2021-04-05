package com.capstone.apm.transaction.response;

public interface Response {
    int getStatusCode();
    String getHeader(String headerName);
}
