package com.example.stage7.CRUD.Elastics.Table.Service;

import com.example.stage7.CRUD.Chart.entity.Chart;
import com.example.stage7.CRUD.Chart.repository.ChartRepository;
import com.example.stage7.CRUD.Elastics.ChartSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TableService {

    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private ChartSyncService chartSyncService;


    public Map<String, Double> tableValues() {

        chartSyncService.syncChartsFromElasticsearchToMongoDB();

        Iterable<Chart> chartList = chartRepository.findAll();

        double totalBonds = 0.0;
        double totalEquities = 0.0;

        for (Chart chart : chartList) {
            String assetType = chart.getX_axis();
            double value;

            try {
                value = Double.parseDouble(chart.getY_axis());
            } catch (NumberFormatException e) {
                continue; // Skip this entry if the value is not a valid number
            }

            if ("Bonds".equalsIgnoreCase(assetType)) {
                totalBonds += value;
            } else if ("Equities".equalsIgnoreCase(assetType)) {
                totalEquities += value;
            }
        }
        double totalSum = totalBonds + totalEquities;

        Map<String, Double> result = new HashMap<>();
        result.put("Bonds", totalBonds);
        result.put("Equities", totalEquities);
        result.put("Total", totalSum); // Adding total sum to the result
        return result;


    }
}

