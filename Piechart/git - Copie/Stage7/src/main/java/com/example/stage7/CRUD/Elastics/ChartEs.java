package com.example.stage7.CRUD.Elastics;

import com.example.stage7.CRUD.Chart.entity.charttype;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Document(indexName = "data_aum")
public class ChartEs {
    @Id
    private String id;
    @Field(name = "Date", type = FieldType.Text)
    private String date;
    @Field(name = "Client", type = FieldType.Text)
    private String client;
    @Field(name = "Asset Type", type = FieldType.Text)
    private String assetType;
    @Field(name = "value (K€)", type = FieldType.Text) // Changed to Text
    private String value; // Changed to String for calculations

}

