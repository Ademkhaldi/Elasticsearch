package com.example.elasticsearchlogstash;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataAumRepository extends ElasticsearchRepository<DataAum, String> {

}
