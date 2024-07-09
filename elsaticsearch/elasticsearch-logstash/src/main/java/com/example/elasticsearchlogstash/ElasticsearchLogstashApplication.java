package com.example.elasticsearchlogstash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class ElasticsearchLogstashApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchLogstashApplication.class, args);
    }

}
