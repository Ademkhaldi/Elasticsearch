package com.example.stage7.CRUD.Elastics.Histogram.service;

import com.example.stage7.CRUD.Chart2.entity.Chart2;
import com.example.stage7.CRUD.Chart2.repository.Chart2Repository;
import com.example.stage7.CRUD.Elastics.ChartSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistogramService {

    @Autowired
    private Chart2Repository chart2Repository;

    @Autowired
    private ChartSyncService chartSyncService;

    public Map<String, Double> calculateHistogramData() {
        // Synchronize charts from Elasticsearch to MongoDB if needed
        chartSyncService.syncChartsFromElasticsearchToMongoDB2();

        List<Chart2> chart2List = chart2Repository.findAll();

        // Calculate histogram data (variation of value with date)
        Map<String, Double> histogramData = new HashMap<>();

        for (Chart2 chart2 : chart2List) {
            String date = chart2.getX_axis();
            double value;

            try {
                value = Double.parseDouble(chart2.getY_axis());
            } catch (NumberFormatException e) {
                continue; // Skip this entry if the value is not a valid number
            }

            // Accumulate values based on date
            histogramData.put(date, histogramData.getOrDefault(date, 0.0) + value);
        }

        return histogramData;
    }
}
