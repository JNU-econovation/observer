package com.capstone.apm.collector.agent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public String createAgent(String name) {
        Agent agent = Agent.create(name);
        agentRepository.save(agent);
        return agent.getId();
    }

    public Agent findById(String id) {
        return agentRepository.findById(id).get();
    }
}
