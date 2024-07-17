package com.example.stage7.CRUD.Elastics;

import com.example.stage7.CRUD.Chart.entity.Chart;
import com.example.stage7.CRUD.Chart.repository.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ChartSyncService {

    @Autowired
    private ChartEsRepository chartEsRepository;

    @Autowired
    private ChartRepository chartRepository;

    @Scheduled(cron = "* * * * * *") // Exécuter toutes les heures
    public void syncChartsFromElasticsearchToMongoDB() {
        Iterable<ChartEs> chartEsList = chartEsRepository.findAll();

        for (ChartEs chartEs : chartEsList) {
            Chart chart = new Chart();
            chart.setX_axis(chartEs.getAssetType());
            chart.setY_axis(chartEs.getValue());
            chart.setIndex(chartEs.getIndexName()); // If method exists

            // Assurez-vous d'ajuster les autres champs et associations si nécessaires (Datasource, Portlet, etc.)

            // Sauvegardez ou mettez à jour dans MongoDB
            chartRepository.save(chart);
        }
    }
}

