package com.example.stage7.CRUD.Elastics.Histogram.controller;

import com.example.stage7.CRUD.Elastics.Histogram.service.HistogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/histogram")
public class HistogramController {

    @Autowired
    private HistogramService histogramService;

    @GetMapping("/data")
    public Map<String, Double> getHistogramData() {
        return histogramService.calculateHistogramData();
    }
}
