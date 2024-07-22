package com.example.stage7.CRUD.Elastics.Camambert.Controller;


import com.example.stage7.CRUD.Elastics.Camambert.Service.ChartCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Map;

@RestController
@RequestMapping("/api/charts")
public class ChartCalculationController {

    @Autowired
    private ChartCalculationService chartCalculationService;

    @GetMapping("/pie-chart-data")
    public Map<String, String> getSumValues() {
        Map<String, Double> rawValues = chartCalculationService.calculateValues();
        DecimalFormat df = new DecimalFormat("##############.#######");

        // Convert to formatted strings
        return Map.of(
                "Equities", df.format(rawValues.get("Equities")),
                "Bonds", df.format(rawValues.get("Bonds"))
        );
    }
}
