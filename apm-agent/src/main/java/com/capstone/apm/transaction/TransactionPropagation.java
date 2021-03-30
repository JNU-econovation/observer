package com.capstone.apm.transaction;

import java.util.List;
import java.util.Map;

/*
* 분산 트랜잭션을 추적하기 위해 Http Header에 Trace-Id를 삽입해야한다.
* 관련된 메서드를 제공하는 Interface
 */
interface TransactionPropagation {
    void injectTraceId(Map<String, List<String>> headers);
}
