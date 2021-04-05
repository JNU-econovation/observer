package com.capstone.apm.transaction;

import com.capstone.apm.event.EventPublisher;
import com.capstone.apm.transaction.event.TransactionEvent;
import com.capstone.apm.transaction.request.Request;
import com.capstone.apm.transaction.response.Response;

import static java.util.Objects.requireNonNull;

/*
* TransactionLifeCycle 의 구현체
 */
class DefaultTransactionLifeCycle implements TransactionLifeCycle {

    private final TransactionRepository transactionRepository;
    private final EventPublisher eventPublisher;

    DefaultTransactionLifeCycle(TransactionRepository transactionRepository, EventPublisher eventPublisher) {
        this.transactionRepository = transactionRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void startTransaction(Request request) {
        Transaction transaction = new Transaction();
        transaction.start(request);

        transactionRepository.addTransaction(transaction);

        TransactionDto transactionDto = TransactionDto.of(transaction);
        eventPublisher.publishEvent(new TransactionEvent(transactionDto));
    }

    @Override
    public void endTransaction(Response response) {
        Transaction transaction = transactionRepository.getTransaction();
        transaction.end(response);

        TransactionDto transactionDto = TransactionDto.of(transaction);
        eventPublisher.publishEvent(new TransactionEvent(transactionDto));

        transactionRepository.removeTransaction();
    }
}
