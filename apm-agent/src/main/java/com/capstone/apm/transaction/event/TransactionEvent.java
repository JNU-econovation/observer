package com.capstone.apm.transaction.event;

import com.capstone.apm.event.Event;
import com.capstone.apm.transaction.TransactionDto;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

public class TransactionEvent extends Event<TransactionDto> {

    public TransactionEvent(TransactionDto transactionDto) {
        super(transactionDto);
    }

    @Override
    public void run() {
        System.out.println(content);
    }
}
