package com.capstone.apm.transaction.request;


public interface Request {
    String getRemoteAddr();
    String getRequestURI();
    String getServerName();
    String getServerPort();
    String getHeader(String s);
}
