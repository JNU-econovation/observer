package com.capstone.apm.transaction.request;


public interface Request {
    String getRemoteAddr();
    String getRequestURI();
    String getHeader(String s);
}
