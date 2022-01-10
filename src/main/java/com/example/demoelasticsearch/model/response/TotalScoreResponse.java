package com.example.demoelasticsearch.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalScoreResponse {

    private String name;

    private Float sumScore;
}
