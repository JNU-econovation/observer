package com.capstone.apm.collector;

import com.capstone.apm.collector.agent.Agent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HealthService {

    private final MongoTemplate mongoTemplate;

    public String saveAgent(String agentName) {
        Agent agent = Agent.create(agentName);
        mongoTemplate.insert(agent);
        return agent.getId();
    }

    public Agent getAgent(String id) {
        Agent agent = mongoTemplate.findById(id, Agent.class);
        return agent;
    }
}
