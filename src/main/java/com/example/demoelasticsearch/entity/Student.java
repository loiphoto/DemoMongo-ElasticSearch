package com.example.demoelasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "person", type = "student")
@Data
public class Student {

    @Id
    private String studentId;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Integer, name = "age")
    private Integer age;

}
