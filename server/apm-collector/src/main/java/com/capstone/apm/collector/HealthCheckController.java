package com.capstone.apm.collector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {

    @GetMapping("/server/list")
    public ResponseEntity<?> getServerList() {
        return ResponseEntity.ok("Get Server List");
    }

    @PostMapping("/birth")
    public ResponseEntity<?> getBirthBit() {
        return ResponseEntity.ok("Get Birth Bit in Collector Server");
    }

    @PostMapping("/dead")
    public ResponseEntity<?> dead() {
        return ResponseEntity.ok("Get Dead Bit in Collector Server");
    }

    @PostMapping("/heart")
    public ResponseEntity<?> heartBit() {
        return ResponseEntity.ok("Get Heart Bit in Collector Server");
    }
}
