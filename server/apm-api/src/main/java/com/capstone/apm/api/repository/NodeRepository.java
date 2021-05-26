package com.capstone.apm.api.repository;

import com.capstone.apm.api.domain.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NodeRepository extends MongoRepository<Node, String> {
}
