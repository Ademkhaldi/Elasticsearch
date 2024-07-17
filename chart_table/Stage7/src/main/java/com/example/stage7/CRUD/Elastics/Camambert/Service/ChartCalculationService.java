package com.example.stage7.CRUD.Elastics.Camambert.Service;

import com.example.stage7.CRUD.Elastics.ChartEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChartCalculationService {

    @Autowired
    private com.example.stage7.CRUD.Elastics.ChartEsRepository ChartEsRepository;


    public Map<String, Double> calculateValues() {
        Iterable<ChartEs> chartEsList = ChartEsRepository.findAll();
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

        Map<String, Double> result = new HashMap<>();
        result.put("Bonds", totalBonds);
        result.put("Equities", totalEquities);
        return result;
    }
  /*
    private String[] generateRandomColors(int count) {
        String[] colors = new String[count];
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String color = String.format("#%06X", random.nextInt(0xFFFFFF + 1));
            colors[i] = color;
        }

        return colors;
    }
*/
}
