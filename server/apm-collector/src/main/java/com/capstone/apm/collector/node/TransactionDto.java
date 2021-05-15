package com.capstone.apm.collector.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {

    private String serverName;

    private String transactionStatus;

    private String traceId;

    private String addr;

    private String remoteAddr;

    private String remoteServerType;

    private long startTransactionTime;

    private long endTransactionTime;

    private int statusCode;

    private int sequence;
}
