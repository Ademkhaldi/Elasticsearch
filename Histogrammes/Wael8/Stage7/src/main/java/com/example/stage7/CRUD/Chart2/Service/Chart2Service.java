package com.example.stage7.CRUD.Chart2.Service;


import com.example.stage7.CRUD.Chart2.entity.Chart2;
import com.example.stage7.CRUD.Chart2.repository.Chart2Repository;
import com.example.stage7.CRUD.Datasource.entity.Datasource;
import com.example.stage7.CRUD.Datasource.repository.DatasourceRepository;
import com.example.stage7.CRUD.Portlet.entity.Portlet;
import com.example.stage7.CRUD.Portlet.repository.PortletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class Chart2Service implements IChart2Service {

    @Autowired
    private Chart2Repository chart2Repository;

   @Autowired
   private PortletRepository portletRepository;
    @Autowired
    private DatasourceRepository datasourceRepository;

    @Override

    public List<Chart2> getAllCharts2() {
        return chart2Repository.findAll();
    }

    @Override
    public Chart2 retrieveChart2(String id) {
        return chart2Repository.findById(id).orElse(null);
    }

    @Override
    public Chart2 createChart2(Chart2 chart) {
        chart.setCreationDate(new Date()); // Utilise la date et l'heure actuelles lors de la création
        chart.setUpdate_date(chart.getCreationDate()); // Met à jour la date de création
        return chart2Repository.save(chart);
    }

    @Override
    public Chart2 updateChart2(String id, Chart2 chart2) {
        Optional<Chart2> existingChart2Optional = chart2Repository.findById(id);
        if (existingChart2Optional.isPresent()) {
            Chart2 existingChart2 = existingChart2Optional.get();
            existingChart2.setTitle(chart2.getTitle());
            existingChart2.setType(chart2.getType());
            existingChart2.setX_axis(chart2.getX_axis());
            existingChart2.setY_axis(chart2.getY_axis());
            existingChart2.setIndex(chart2.getIndex());

            // Mise à jour de la date de mise à jour et de l'identifiant du metteur à jour
            existingChart2.setUpdate_date(new Date());
            existingChart2.setUpdator_id(chart2.getUpdator_id());

            return chart2Repository.save(existingChart2);
        } else {
            return null; // Gérer l'absence de l'élément à mettre à jour comme vous le souhaitez
        }
    }


    @Override

    public boolean deleteChart2(String id) {
        if (chart2Repository.existsById(id)) {
            chart2Repository.deleteById(id);
            return true;
        } else {
            return false; // Gérer l'absence de l'élément à supprimer comme vous le souhaitez
        }
    }

    @Override
    public boolean deleteAllCharts2() {
        long countBeforeDelete = chart2Repository.count();
        chart2Repository.deleteAll();
        long countAfterDelete = chart2Repository.count();
        return countBeforeDelete != countAfterDelete;

    }



    @Override
    public Chart2 retrieveTitle(String title) {
        return chart2Repository.findByTitle(title).orElse(null);
    }


    //Affectation

    @Override
    public boolean affecterDatasourceAChart2(String idChart, String idDatasource) {
        Optional<Chart2> optionalChart2 = chart2Repository.findById(idChart);
        Optional<Datasource> optionalDatasource = datasourceRepository.findById(idDatasource);

        if (optionalChart2.isPresent() && optionalDatasource.isPresent()) {
            Chart2 chart2 = optionalChart2.get();
            Datasource datasource = optionalDatasource.get();
            chart2.setDatasource(datasource);
            chart2Repository.save(chart2);
            return true;
        } else {
            return false;
        }
    }


    public boolean affecterPortletAChart2(String idChart,String idPortlet) {
        Optional<Chart2> optionalChart2 = chart2Repository.findById(idChart);
        Optional<Portlet> optionalPortlet = portletRepository.findById(idPortlet);

        if (optionalChart2.isPresent() && optionalPortlet.isPresent()) {
            Chart2 chart2 = optionalChart2.get();
            Portlet portlet = optionalPortlet.get();
            chart2.setPortlet(portlet);
            chart2Repository.save(chart2);
            return true;

        } else {
            // Gérer le cas où le portlet ou le tableau de bord n'est pas trouvé
            // Vous pouvez lancer une exception appropriée ou renvoyer null, selon vos besoins
            //System.out.println("Portlet ou Dashboard non trouvé");
            return false;

        }
    }




}