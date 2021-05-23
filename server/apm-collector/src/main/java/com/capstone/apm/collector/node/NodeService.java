package com.capstone.apm.collector.node;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public String save(Transaction transaction, String serviceName, String serverName) {
        Node node = nodeRepository.findByServiceName(serviceName).orElseGet(() -> Node.create(serviceName));
        Agent findAgent = node.getAgents().stream()
                .filter(agent -> agent.getServerName().equals(serverName))
                .findFirst()
                .orElseGet(() -> Agent.create(node, serverName));

        findAgent.saveTransaction(transaction);

        nodeRepository.save(node);
        return node.getId();
    }

    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

    public Node findByNodeId(String nodeId){
        return nodeRepository.findById(nodeId).orElseThrow(() -> new IllegalArgumentException("Not Found"));
    }
}
