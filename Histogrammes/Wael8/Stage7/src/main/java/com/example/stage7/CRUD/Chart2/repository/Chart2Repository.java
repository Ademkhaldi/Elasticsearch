package com.example.stage7.CRUD.Chart2.repository;
import com.example.stage7.CRUD.Chart2.entity.Chart2;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Chart2Repository extends MongoRepository<Chart2, String> {


    Optional<Chart2> findByTitle(String title);
}
