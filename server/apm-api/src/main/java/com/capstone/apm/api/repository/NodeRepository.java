package com.capstone.apm.api.repository;

import com.capstone.apm.api.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NodeRepository extends MongoRepository<Node, String> {

    Optional<Node> findByServiceName(String serviceName);
}
