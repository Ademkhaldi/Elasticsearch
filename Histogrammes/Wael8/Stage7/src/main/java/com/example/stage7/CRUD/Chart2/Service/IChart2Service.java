package com.example.stage7.CRUD.Chart2.Service;


import com.example.stage7.CRUD.Chart2.entity.Chart2;

import java.util.List;

public interface IChart2Service {


    List<Chart2> getAllCharts2();

    Chart2 retrieveChart2(String id);

    Chart2 createChart2(Chart2 chart2);

    Chart2 updateChart2(String id, Chart2 chart2);

    boolean deleteChart2(String id);

    boolean deleteAllCharts2();

    Chart2 retrieveTitle(String title);



    boolean affecterDatasourceAChart2(String idChart, String idDatasource);

    boolean affecterPortletAChart2(String idChart,String idPortlet);



    }
