package com.capstone.apm.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@Getter
@Setter
public class Node {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String serviceName;
    
    private List<Agent> agents = new ArrayList<>();
    
    public static Node create(String serviceName) {
        Node node = new Node();
        node.serviceName = serviceName;
        return node;
    }
    
    public void saveAgent(Agent agent) {
        this.agents.add(agent);
    }
}
