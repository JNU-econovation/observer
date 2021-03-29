package com.capstone.apm.transaction;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
