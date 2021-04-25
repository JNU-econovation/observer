package com.capstone.apm.collector.agent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "agents")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter(AccessLevel.PRIVATE)
public class Agent {

    @Id
    private String id;

    private String name;


    public static Agent create(String name) {
        Agent agent = new Agent();
        agent.setName(name);
        return agent;
    }
}
