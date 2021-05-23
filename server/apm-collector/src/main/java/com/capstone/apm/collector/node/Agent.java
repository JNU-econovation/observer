package com.capstone.apm.collector.node;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Agent {
    @Id
    private String id;

    @Indexed(unique=true)
    private String serverName;

    private List<Transaction> transactions = new ArrayList<>();

    public static Agent create(Node node, String serverName){
        Agent agent = new Agent();
        agent.serverName = serverName;
        node.saveAgent(agent);
        return agent;
    }

    public void saveTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
