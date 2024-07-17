package com.example.stage7;

import com.example.stage7.CRUD.Elastics.ChartSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Stage7Application implements CommandLineRunner {

    @Autowired
    private ChartSyncService chartSyncService;

    public static void main(String[] args) {
        SpringApplication.run(Stage7Application.class, args);
    }

    @Override
    public void run(String... args) {
        chartSyncService.syncChartsFromElasticsearchToMongoDB();
    }
}
