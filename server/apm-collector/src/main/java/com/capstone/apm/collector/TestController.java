package com.capstone.apm.collector;

import com.capstone.apm.collector.node.Node;
import com.capstone.apm.collector.node.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final NodeService nodeService;

    @GetMapping("/nodes")
    public List<Node> getAllNodes() {
        return nodeService.findAll();
    }
}
