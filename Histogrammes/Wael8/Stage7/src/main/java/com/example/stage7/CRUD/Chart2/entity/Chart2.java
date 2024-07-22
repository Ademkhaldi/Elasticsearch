package com.example.stage7.CRUD.Chart2.entity;

import com.example.stage7.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage7.CRUD.Datasource.entity.Datasource;
import com.example.stage7.CRUD.Portlet.entity.Portlet;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "chart2")
@TypeAlias("chart2")
@AllArgsConstructor
@NoArgsConstructor
public class Chart2 extends BusinessEntity {

    private String title;
    private charttype type;
    private String x_axis;
    private String y_axis;
    private String index;


    @DBRef
    private Datasource datasource;


    @DBRef
    private Portlet portlet;


}
