package com.example.elasticsearchlogstash;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Field;

@Setter
@Getter
@ToString
@Document(indexName = "data_aum")
public class DataAum {
    @Id
    private String id;
    @Field(name = "Date", type = FieldType.Text)
    private String date;
    @Field(name = "Client", type = FieldType.Text)
    private String client;
    @Field(name = "Asset Type", type = FieldType.Text)
    private String assetType;
    @Field(name = "value (K€)", type = FieldType.Text)
    private String value;
}
