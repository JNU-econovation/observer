package com.capstone.apm.api.controller;

import com.capstone.apm.api.Agent;
import com.capstone.apm.api.Node;
import com.capstone.apm.api.Transaction;
import com.capstone.apm.api.repository.NodeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.*;

@RestController
public class ServerMapController {
    
    private final NodeRepository nodeRepository;
    
    public ServerMapController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }
    
    @GetMapping("/edges")
    public ResponseEntity getEdges() {
        Map<String, Object> result = new HashMap<>();
        String key = "edges";
        List<EdgeResponseDTO> value = new ArrayList<>();
        List<Node> nodes = nodeRepository.findAll();

        for (Node node : nodes) {
            for (Agent agent : node.getAgents()) {
                List<Transaction> transactions = agent.getTransactions();
                if (transactions.isEmpty()) {
                    break;
                } else {
                    Transaction transaction = transactions.get(0);
                    String clientAddr = transaction.getClientAddr();
                    String remoteAddr = transaction.getRemoteAddr();
                    EdgeResponseDTO edgeResponseDTO =
                            new EdgeResponseDTO(clientAddr, remoteAddr);
                    value.add(edgeResponseDTO);
                    break;
                }
            }
        }
        
        result.put(key, value);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/nodes/{serviceName}/statistics")
    public ResponseEntity getNodeStatistics(@PathVariable("serviceName") String serviceName) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<TransactionResponseDTO> successTransactions = new ArrayList<>();
        List<TransactionResponseDTO> failTransactions = new ArrayList<>();

        Node node = nodeRepository.findByServiceName(serviceName).orElseThrow(RuntimeException::new);
        List<Agent> agents = node.getAgents();
        for (Agent agent : agents) {
            List<Transaction> transactions = agent.getTransactions();
            for (Transaction transaction : transactions) {
                int statusCode = transaction.getStatusCode();
                int hour = new Date(transaction.getTransactionStartTime()).toInstant()
                        .atZone(ZoneId.systemDefault()).getHour();
                int minute = new Date(transaction.getTransactionStartTime()).toInstant()
                        .atZone(ZoneId.systemDefault()).getMinute();
                int transactionMinute = hour * 60 + minute;
                if (isSuccessStatusCode(statusCode)) {
                    successTransactions.add(
                            new TransactionResponseDTO(
                                    transactionMinute,
                                    transaction.getTransactionTimeMillis()
                            )
                    );
                } else {
                    failTransactions.add(
                            new TransactionResponseDTO(
                                    transactionMinute,
                                    transaction.getTransactionTimeMillis()
                            )
                    );
                }
            }
        }

        int successTransactionCount = successTransactions.size();
        int failTransactionCount = failTransactions.size();

        result.put("successList", successTransactions);
        result.put("failList", failTransactions);
        result.put("successNum", successTransactionCount);
        result.put("failNum", failTransactionCount);

        return ResponseEntity.ok(result);
    }

    private boolean isSuccessStatusCode(int statusCode) {
        int flag = statusCode / 100;
        if (flag == 5) {
            return false;
        }

        return true;
    }

    @GetMapping("/nodes")
    public ResponseEntity getNodes() {
        Map<String, Object> result = new HashMap<>();
        List<NodeResponseDTO> nodeResult = new ArrayList<>();
        
        List<Node> nodes = nodeRepository.findAll();
        for (Node node : nodes) {
            List<Agent> agents = node.getAgents();
            String nodeName = node.getServiceName();
            for (Agent agent : agents) {
                List<Transaction> transactions = agent.getTransactions();
                if (transactions.isEmpty()) {
                    break;
                } else {
                    Transaction transaction = transactions.get(0);
                    String remoteAddr = transaction.getRemoteAddr();
                    NodeResponseDTO nodeResponseDTO = new NodeResponseDTO(remoteAddr, nodeName);
                    nodeResult.add(nodeResponseDTO);
                }
            }
        }
        
        Integer count = nodeResult.size();
        result.put("size", count);
        result.put("nodes", nodeResult);
        
        return ResponseEntity.ok(result);
    }
    
    @AllArgsConstructor
    @Getter
    private static class EdgeResponseDTO {
        private String clientAddr;
        private String remoteAddr;
    }
    
    @AllArgsConstructor
    @Getter
    private static class NodeResponseDTO {
        private String address;
        private String name;
    }

    @AllArgsConstructor
    @Getter
    private static class TransactionResponseDTO {
        private long transactionStartTime;
        private long transactionTimeMillis;
    }
}