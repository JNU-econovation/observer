package com.capstone.apm.transaction;

import com.capstone.apm.collector.CollectorClient;
import com.capstone.apm.commons.event.EventPublisher;
import com.capstone.apm.commons.event.EventSubscriber;
import com.capstone.apm.transaction.event.TransactionEvent;
import com.capstone.apm.transaction.event.TransactionEventPublisher;
import com.capstone.apm.transaction.event.TransactionEventSubscriber;
import com.capstone.apm.transaction.request.Request;
import com.capstone.apm.transaction.response.Response;
import com.capstone.apm.commons.event.EventConfiguration;
import com.capstone.apm.transaction.websocket.ServerConfiguration;

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
    private final TransactionEventPublisher eventPublisher;

    private TransactionContext() {
        /*
        * Dependency 생성 나중에 리팩터링하기
         */
        this.transactionRepository = new TransactionRepository();
        this.transactionPropagation = new DefaultTransactionPropagation(transactionRepository);

        this.eventPublisher = new TransactionEventPublisher();
        this.eventPublisher.subscribe(new TransactionEventSubscriber(new EventConfiguration(1)));
        this.transactionLifeCycle = new DefaultTransactionLifeCycle(transactionRepository, eventPublisher);
    }

    public void subscribe(EventSubscriber<? super TransactionEvent> subscriber){
        this.eventPublisher.subscribe(subscriber);
    }

    public static TransactionContext getTransactionContext() {
        return transactionContext;
    }

    @Override
    public void startTransaction(Request request) {
        transactionLifeCycle.startTransaction(request);
    }

    @Override
    public void endTransaction(Response response) {
        transactionLifeCycle.endTransaction(response);
    }

    @Override
    public void propagate(Map<String, List<String>> headers) {
        if(transactionRepository.hasTransaction())
            transactionPropagation.propagate(headers);
    }

    public String getTransactionAsString() {
        return transactionRepository.getTransactionAsString();
    }

}
