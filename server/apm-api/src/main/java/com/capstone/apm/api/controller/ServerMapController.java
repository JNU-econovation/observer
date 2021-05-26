package com.capstone.apm.api.controller;

import com.capstone.apm.api.domain.Agent;
import com.capstone.apm.api.domain.Node;
import com.capstone.apm.api.domain.Transaction;
import com.capstone.apm.api.repository.NodeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServerMapController {
    
    private final NodeRepository nodeRepository;
    
    public ServerMapController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }
    
    @GetMapping("/edges")
    public ResponseEntity getEdges() {
        LinkedMultiValueMap<String, List<EdgeResponseDTO>> result
                = new LinkedMultiValueMap();
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
        
        result.add(key, value);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/nodes/{name}/statistics")
    public ResponseEntity getNodeStatistics(@PathVariable("id") Integer id) {
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/nodes")
    public ResponseEntity getNodes() {
    }
    
    @AllArgsConstructor
    @Getter
    private static class EdgeResponseDTO {
        private String clientAddr;
        private String remoteAddr;
    }
}