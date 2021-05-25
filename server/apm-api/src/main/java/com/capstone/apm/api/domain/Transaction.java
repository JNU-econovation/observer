package com.capstone.apm.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    
    private String transactionStatus;
    
    private String traceId;
    
    private String clientAddr;
    
    private String remoteAddr;
    
    private String remoteServerType;
    
    private long transactionTimeMillis;
    
    private int statusCode;
    
    private int sequence;
}

