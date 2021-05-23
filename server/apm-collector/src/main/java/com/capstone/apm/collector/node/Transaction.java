package com.capstone.apm.collector.node;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter @Setter
public class Transaction {

    @Id
    private String id;

    private String transactionStatus;

    private String traceId;

    private String remoteAddr;

    private String remoteServerType;

    private long transactionTimeMillis;

    private int statusCode;

    private int sequence;
}
