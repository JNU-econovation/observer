package com.capstone.apm.transaction;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;
import java.util.Map;

/*
* Singleton으로, Transformer의 Interceptor들에게 호출된다.
* Interceptor의 진입점을 하나로 통일하기 위해 Context는 Facade Pattern을 이용하고 메서드들을 전부 관련된 객체로 위임한다.
 */
public class TransactionContext implements TransactionLifeCycle, TransactionPropagation {

    private static final TransactionContext transactionContext = new TransactionContext();

    private final TransactionRepository transactionRepository;
    private final TransactionLifeCycle transactionLifeCycle;
    private final TransactionPropagation transactionPropagation;


    private TransactionContext() {
        /*
        * Dependency 생성 나중에 리팩터링하기
         */
        this.transactionRepository = new TransactionRepository();
        this.transactionPropagation = new DefaultTransactionPropagation(transactionRepository);
        this.transactionLifeCycle = new DefaultTransactionLifeCycle(transactionRepository);
    }

    public static TransactionContext getTransactionContext() {
        return transactionContext;
    }

    @Override
    public void startTransaction(ServletRequest servletRequest) {
        transactionLifeCycle.startTransaction(servletRequest);
    }

    @Override
    public void endTransaction(ServletResponse servletResponse) {
        transactionLifeCycle.endTransaction(servletResponse);
    }

    @Override
    public void modifyHeader(Map<String, List<String>> headers) {
        transactionPropagation.modifyHeader(headers);
    }

    public String getTransactionAsString() {
        return transactionRepository.getTransactionAsString();
    }

}
