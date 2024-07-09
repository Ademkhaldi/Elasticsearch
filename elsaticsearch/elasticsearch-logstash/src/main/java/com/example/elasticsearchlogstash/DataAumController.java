package com.example.elasticsearchlogstash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/data_aum")
public class DataAumController {
    private static final Logger logger = LoggerFactory.getLogger(DataAumController.class);

    @Autowired
    private DataAumRepository repository;

    @GetMapping("/{id}")
    public Optional<DataAum> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping("/Data")
    public List<DataAum> findAll() {
        List<DataAum> allData = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        logger.info("FindAll - Retrieved data size: {}", allData.size());
        return allData;
    }

    @GetMapping("/search")
    public List<DataAum> search(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String assetType,
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) String id
    ) {
        List<DataAum> allData = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        logger.info("Search - Retrieved data size before filtering: {}", allData.size());

        List<DataAum> filteredData = allData.stream()
                .filter(dataAum -> (date == null || dataAum.getDate().equals(date)) &&
                        (assetType == null || dataAum.getAssetType().equals(assetType)) &&
                        (client == null || dataAum.getClient().equals(client)) &&
                        (id == null || dataAum.getId().equals(id)) &&
                        (value == null || value.equals(dataAum.getValue())))
                .collect(Collectors.toList());

        logger.info("Search - Filtered data size: {}", filteredData.size());
        return filteredData;
    }
}
