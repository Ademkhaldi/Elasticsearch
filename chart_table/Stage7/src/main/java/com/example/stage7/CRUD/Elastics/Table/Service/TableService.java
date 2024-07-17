package com.example.stage7.CRUD.Elastics.Table.Service;

import com.example.stage7.CRUD.Elastics.ChartEs;
import com.example.stage7.CRUD.Elastics.ChartEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TableService {

    @Autowired
    private ChartEsRepository chartEsRepository;

    public Map<String, Double> tableValues() {
        Iterable<ChartEs> chartEsList = chartEsRepository.findAll();
        double totalBonds = 0.0;
        double totalEquities = 0.0;

        for (ChartEs chartEs : chartEsList) {
            String assetType = chartEs.getAssetType();
            double value = Double.parseDouble(chartEs.getValue());

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
