package com.example.demoelasticsearch.repository.elasticsearch.impl;

import com.example.demoelasticsearch.constant.StudentConstant;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
@Slf4j
public class StudentRepositoryDao {

    @Autowired
    private RestHighLevelClient client;

    public SearchResponse totalScore(){
        try {
            SearchRequest searchRequest = new SearchRequest(StudentConstant.INDEX);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.scriptField(StudentConstant.TOTAL_SCORE, new Script("doc['math_score'].value + doc['physic_score'].value + doc['chemistry_score'].value"));
            searchSourceBuilder.fetchField(StudentConstant.NAME);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            for (org.elasticsearch.search.SearchHit hit : searchResponse.getHits()) {
                Map<String, DocumentField> fields = hit.getFields();
                log.info("Field" + fields);
            }
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SearchResponse averageScore(){
        try {
            SearchRequest searchRequest = new SearchRequest(StudentConstant.INDEX);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.scriptField(StudentConstant.TOTAL_SCORE, new Script("(doc['math_score'].value + doc['physic_score'].value + doc['chemistry_score'].value)/3"));
            searchSourceBuilder.fetchField(StudentConstant.NAME);

            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            for (org.elasticsearch.search.SearchHit hit : searchResponse.getHits()) {
                Map<String, DocumentField> fields = hit.getFields();
                log.info("Field" + fields.values());
            }
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
