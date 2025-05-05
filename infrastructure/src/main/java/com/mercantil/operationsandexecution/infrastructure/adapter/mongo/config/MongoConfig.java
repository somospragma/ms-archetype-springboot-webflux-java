package com.mercantil.operationsandexecution.infrastructure.adapter.mongo.config;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(@Value("${mongodb.uri}") String uri) {
        return MongoClients.create(uri);
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory(MongoClient mongoClient,
                                                         @Value("${mongodb.database}") String database) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, database);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory databaseFactory) {
        return new ReactiveMongoTemplate(databaseFactory);
    }
}
