package com.capstone.apm.transaction.event;

import com.capstone.apm.commons.event.Event;
import com.capstone.apm.transaction.TransactionDto;

public class TransactionEvent extends Event<TransactionDto> {
    public TransactionEvent(TransactionDto content) {
        super(content);
    }
}
