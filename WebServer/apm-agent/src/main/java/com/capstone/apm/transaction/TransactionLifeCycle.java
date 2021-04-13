package com.capstone.apm.transaction;

import com.capstone.apm.transaction.request.Request;
import com.capstone.apm.transaction.response.Response;

/*
* TransactionLifeCycle Interface 이다.
* 요청과 동시에 start 해야하며,
* 응답과 동시에 end 해야한다.
 */
interface TransactionLifeCycle {

    /*
    * 트랜잭션의 시작점
     */
    void startTransaction(Request request);

    /*
     * 트랜잭션의 종료지점
     */
    void endTransaction(Response response);
}
