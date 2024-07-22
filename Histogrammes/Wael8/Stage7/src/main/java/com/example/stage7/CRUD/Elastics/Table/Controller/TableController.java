package com.example.stage7.CRUD.Elastics.Table.Controller;

import com.example.stage7.CRUD.Elastics.Table.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/charts")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/table-chart-data")
    public Map<String, String> tableValues() {
        Map<String, Double> rawValues = tableService.tableValues();
        DecimalFormat df = new DecimalFormat("##############.#######");

        // Convert to formatted strings and include total
        Map<String, String> formattedValues = new HashMap<>();
        formattedValues.put("Equities", df.format(rawValues.get("Equities")));
        formattedValues.put("Bonds", df.format(rawValues.get("Bonds")));
        formattedValues.put("Total", df.format(rawValues.get("Total")));

        return formattedValues;
    }
}



