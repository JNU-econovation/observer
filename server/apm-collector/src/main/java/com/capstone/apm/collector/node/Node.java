package com.capstone.apm.collector.node;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Node {

    @Id
    private String id;

    private String serverName;

    private List<Agent> agents = new ArrayList<>();

    public static Node create(String serverName){
        Node node = new Node();
        node.serverName = serverName;
        return node;
    }

    public void saveAgent(Agent agent){
        this.agents.add(agent);
    }
}

