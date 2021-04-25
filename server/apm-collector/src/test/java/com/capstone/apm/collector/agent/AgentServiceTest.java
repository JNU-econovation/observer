package com.capstone.apm.collector.agent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgentServiceTest {

    @Autowired
    AgentService agentService;

    @DisplayName("MongoDB Test")
    @Test
    public void saveAndFindTest() {
        String serverName = "Server1";
        String id = agentService.createAgent(serverName);
        Assertions.assertNotNull(id);
        Agent agent = agentService.findById(id);

        assertEquals(serverName, agent.getName());
    }
}