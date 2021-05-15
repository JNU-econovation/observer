package com.capstone.apm.collector.node;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Agent {

    private String addr;

    private List<Transaction> transactions = new ArrayList<>();

    public static Agent create(Node node, String addr){
        Agent agent = new Agent();
        agent.addr = addr;
        node.saveAgent(agent);
        return agent;
    }

    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setStartTransactionTime(transactionDto.getStartTransactionTime());
        transaction.setEndTransactionTime(transactionDto.getEndTransactionTime());
        transaction.setTransactionStatus(transactionDto.getTransactionStatus());
        transaction.setRemoteAddr(transactionDto.getRemoteAddr());
        transaction.setSequence(transactionDto.getSequence());
        transaction.setStatusCode(transactionDto.getStatusCode());
        transaction.setRemoteServerType(transactionDto.getRemoteServerType());
        this.transactions.add(transaction);
    }
}
