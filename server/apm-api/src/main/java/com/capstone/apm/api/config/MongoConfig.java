package com.capstone.apm.api.config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.capstone.apm.api.repository", mongoTemplateRef = "mongoDBTemplate")
@EnableMongoAuditing
public class MongoConfig {

    // mongoDB Template Config가 기본이나 보다.
    @Bean
    public MongoTemplate mongoDBTemplate(MongoClient mongoClient) {
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, "observer");
        return new MongoTemplate(factory);
    }
}

