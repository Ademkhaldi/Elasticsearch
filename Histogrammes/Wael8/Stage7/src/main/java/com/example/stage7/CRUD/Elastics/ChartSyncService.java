package com.example.stage7.CRUD.Elastics;

import com.example.stage7.CRUD.Chart.entity.Chart;
import com.example.stage7.CRUD.Chart.repository.ChartRepository;
import com.example.stage7.CRUD.Chart2.entity.Chart2;
import com.example.stage7.CRUD.Chart2.repository.Chart2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ChartSyncService {

    @Autowired
    private ChartEsRepository chartEsRepository;

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private Chart2Repository chart2Repository;


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



    public void syncChartsFromElasticsearchToMongoDB2() {
        Iterable<ChartEs> chartEsList = chartEsRepository.findAll();

        for (ChartEs chartEs : chartEsList) {
            Chart2 chart2 = new Chart2();
            chart2.setX_axis(chartEs.getDate());
            chart2.setY_axis(chartEs.getValue());

            // Assurez-vous d'ajuster les autres champs et associations si nécessaires (Datasource, Portlet, etc.)

            // Sauvegardez ou mettez à jour dans MongoDB
            chart2Repository.save(chart2);
        }
    }
}

