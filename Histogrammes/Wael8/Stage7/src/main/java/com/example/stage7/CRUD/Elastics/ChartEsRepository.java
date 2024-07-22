package com.example.stage7.CRUD.Elastics;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartEsRepository extends ElasticsearchRepository<ChartEs, String> {
    // Définissez les méthodes de recherche ou personnalisées si nécessaire
}

