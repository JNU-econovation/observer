package com.capstone.apm.transaction;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
* TransactionLifeCycle Interface 이다.
* 요청과 동시에 start 해야하며,
* 응답과 동시에 end 해야한다.
 */
interface TransactionLifeCycle {

    /*
    * 트랜잭션의 시작점
     */
    void startTransaction(ServletRequest servletRequest);

    /*
     * 트랜잭션의 종료지점
     */
    void endTransaction(ServletResponse servletResponse);
}
