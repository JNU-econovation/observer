package com.capstone.apm.api.controller;

import com.capstone.apm.api.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerMapController {
    
    private final NodeRepository nodeRepository;
    
    public ServerMapController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }
    
    @GetMapping("/edges")
    public ResponseEntity getEdges() {
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/nodes/{name}/statistics")
    public ResponseEntity getNodeStatistics(@PathVariable("id") Integer id) {
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/nodes")
    public ResponseEntity getNodes() {
        
        return ResponseEntity.ok().build();
    }
}
