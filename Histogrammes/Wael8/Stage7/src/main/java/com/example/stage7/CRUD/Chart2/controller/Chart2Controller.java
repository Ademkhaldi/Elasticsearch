package com.example.stage7.CRUD.Chart2.controller;

import com.example.stage7.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage7.CRUD.Chart2.DTO.Chart2DTO;
import com.example.stage7.CRUD.Chart2.Service.IChart2Service;
import com.example.stage7.CRUD.Chart2.entity.Chart2;
import com.example.stage7.CRUD.Chart2.exception.Chart2NotFoundException;
import com.example.stage7.CRUD.Datasource.DTO.DatasourceDTO;
import com.example.stage7.CRUD.Portlet.DTO.PortletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/Charts2")
@FeignClient(name = "APIgateway")

public class Chart2Controller {

    @Autowired
    private IChart2Service chart2Service;



    @GetMapping("/getAllCharts2")
    public List<Chart2DTO> getAllCharts() {
        List<Chart2> charts2 = chart2Service.getAllCharts2();
        List<Chart2DTO> chart2DTOs = new ArrayList<>();
        for (Chart2 chart2 : charts2) {
            Chart2DTO chart2DTO = convertToDTO(chart2);
            chart2DTOs.add(chart2DTO);
        }
        return chart2DTOs;
    }
    private Chart2DTO convertToDTO(Chart2 chart2) {
        Chart2DTO chart2DTO = new Chart2DTO();
        chart2DTO.setTitle(chart2.getTitle());
        chart2DTO.setType(chart2.getType());
        chart2DTO.setX_axis(chart2.getX_axis());
        chart2DTO.setY_axis(chart2.getY_axis());
        chart2DTO.setIndex(chart2.getIndex());

        // Vérifiez d'abord si l'entité commerciale de chart n'est pas nulle
        if (chart2 instanceof BusinessEntity) {
            BusinessEntity businessEntity = (BusinessEntity) chart2;
            chart2DTO.setId(businessEntity.getId());
            chart2DTO.setCreationDate(businessEntity.getCreationDate());
            chart2DTO.setCreator_id(businessEntity.getCreator_id());
            chart2DTO.setUpdate_date(businessEntity.getUpdate_date());
            chart2DTO.setUpdator_id(businessEntity.getUpdator_id());
        }

        if (chart2.getDatasource() != null) {
            DatasourceDTO datasourceDTO = new DatasourceDTO();
            datasourceDTO.setId(chart2.getDatasource().getId());
            datasourceDTO.setType(chart2.getDatasource().getType());
            datasourceDTO.setConnection_port(chart2.getDatasource().getConnection_port());


            datasourceDTO.setUrl(chart2.getDatasource().getUrl());
            datasourceDTO.setUser(chart2.getDatasource().getUser());
            datasourceDTO.setPassword(chart2.getDatasource().getPassword());

            chart2DTO.setDatasource(datasourceDTO);


        }
        if (chart2.getPortlet() != null) {
            PortletDTO portletDTO = new PortletDTO();
            portletDTO.setId(chart2.getPortlet().getId());
            portletDTO.setRow(chart2.getPortlet().getRow());
            portletDTO.setColumn(chart2.getPortlet().getColumn());
            chart2DTO.setPortlet(portletDTO);
        }
        return chart2DTO;
    }



   @GetMapping("/{id}")
   public Chart2DTO retrieveChart2(@PathVariable("id") String id) {
       Chart2 chart2 = chart2Service.retrieveChart2(id);
       return convertToDTO(chart2);
   }



    @GetMapping("/title2/{title}")
    public Chart2DTO retrieveTitle(@PathVariable("title") String title) {
        Chart2 chart2 = chart2Service.retrieveTitle(title);
        if (chart2 != null) {
            return convertToDTO(chart2);
        } else {
            throw new Chart2NotFoundException("Chart with title " + title + " not found");
        }
    }


    @PostMapping("/Add2")
    public ResponseEntity<Chart2> createChart2(@RequestBody Chart2 chart2) {
        Chart2 createChart2 = chart2Service.createChart2(chart2);
        return new ResponseEntity<>(createChart2, HttpStatus.CREATED);
    }

    @PutMapping("/Update2/{id}")
    public ResponseEntity<Map<String, Object>> updateChart2(@PathVariable String id, @RequestBody Chart2 chart2) {
        Chart2 updatedChart2 = chart2Service.updateChart2(id, chart2);
        if (updatedChart2 != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Chart updated successfully");
            response.put("chart", updatedChart2);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Chart not found with id: " + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/Delete2/{id}")
    public ResponseEntity<String> deleteChart2(@PathVariable String id) {
        boolean deleted2 = chart2Service.deleteChart2(id);
        if (deleted2) {
            return new ResponseEntity<>("Chart removed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il n'y a aucun champ à supprimer", HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/deleteAllCharts2")
    public ResponseEntity<String> deleteAllCharts2() {
        try {
            boolean deleted2 = chart2Service.deleteAllCharts2();
            if (deleted2) {
                return ResponseEntity.ok("Charts removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune suppression");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression");
        }
    }

    //Affectation
    //{idPortlet}{idDashboard}
    @PutMapping("/affecterDatasourceAChart2/{idChart}/{idDatasource}")
    public ResponseEntity<Map<String, String>> affecterDatasourceAChart2(
            /*{idPortlet}*/ @PathVariable("idChart") String idChart,
            /*{idDashboard}*/@PathVariable("idDatasource") String idDatasource) {

        boolean affectationReussie2 =chart2Service.affecterDatasourceAChart2(idChart, idDatasource);
        // Créez une carte pour stocker les informations de la réponse
        Map<String, String> response = new HashMap<>();
        if(affectationReussie2){

            // Ajoutez les détails de l'affectation à la réponse
            response.put("message", "Affectation réussie");
            response.put("ChartId", idChart);
            response.put("DatasourceId", idDatasource);

            // Répondez avec un objet ResponseEntity contenant la carte de réponse
            return ResponseEntity.ok(response);
        } else {

            // Si le client ou le marché n'est pas trouvé, ajoutez un message d'erreur à la réponse
            response.put("message", "Chart ou Datasource non trouvé");

            // Répondez avec un statut NOT_FOUND et la carte de réponse
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/affecterPortletAChart2/{idChart}/{idPortlet}")
    public ResponseEntity<Map<String, String>> affecterPortletAChart2(
            /*{idDashboard}*/@PathVariable("idChart") String idChart,
            /*{idPortlet}*/ @PathVariable("idPortlet") String idPortlet) {

        boolean affectationReussie2 =chart2Service.affecterPortletAChart2(idChart,idPortlet);
        // Créez une carte pour stocker les informations de la réponse
        Map<String, String> response = new HashMap<>();
        if(affectationReussie2){

            // Ajoutez les détails de l'affectation à la réponse
            response.put("message", "Affectation réussie");
            response.put("ChartId", idChart);
            response.put("portletId", idPortlet);

            // Répondez avec un objet ResponseEntity contenant la carte de réponse
            return ResponseEntity.ok(response);
        } else {

            // Si le client ou le marché n'est pas trouvé, ajoutez un message d'erreur à la réponse
            response.put("message", "idChart ou Portlet non trouvé");

            // Répondez avec un statut NOT_FOUND et la carte de réponse
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
