package com.example.demoelasticsearch.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String name;

    private String className;

    private Float mathScore;

    private Float physicScore;

    private Float chemistryScore;
}
