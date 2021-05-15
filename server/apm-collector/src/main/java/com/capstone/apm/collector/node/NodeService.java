package com.capstone.apm.collector.node;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public String saveTransaction(TransactionDto transactionDto){
        String serverName = transactionDto.getServerName();
        Node node = nodeRepository.findByServerName(serverName).orElseGet(() -> Node.create(serverName));

        String addr = transactionDto.getAddr();

        Agent findAgent = node.getAgents().stream()
                .filter(agent -> agent.getAddr().equals(addr))
                .findFirst()
                .orElseGet(() -> Agent.create(node, addr));

        findAgent.saveTransaction(transactionDto);

        nodeRepository.save(node);

        return node.getId();
    }

    public Node findByNodeId(String nodeId){
        return nodeRepository.findById(nodeId).orElseThrow(() -> new IllegalArgumentException("Not Found"));
    }
}
