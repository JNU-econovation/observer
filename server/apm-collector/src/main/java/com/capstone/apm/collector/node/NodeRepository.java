package com.capstone.apm.collector.node;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NodeRepository extends MongoRepository<Node, String> {

    Optional<Node> findByServerName(String serverName);
}
