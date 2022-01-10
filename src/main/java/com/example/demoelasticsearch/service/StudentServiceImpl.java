package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.model.response.AverageScoreResponse;
import com.example.demoelasticsearch.model.response.TotalScoreResponse;
import com.example.demoelasticsearch.repository.elasticsearch.base.StudentRepository;
import com.example.demoelasticsearch.repository.mongodb.impl.StudentRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepositoryElasticsearch;

    @Autowired
    private StudentRepositoryImpl studentRepositoryMongo;

    @Autowired
    private RestHighLevelClient client;


    public Iterable<Student> findALL() {
        Iterable<Student> all = studentRepositoryElasticsearch.findAll();
        System.out.println(all);
        return all;
    }

    public void insertMongo(StudentRequest student){
        studentRepositoryMongo.insert(student);
    }

    public List<Student> findAllMongo(){
        List<Student> all = studentRepositoryMongo.findAll();
        return all;
    }

    public void deleteAll() {
        System.out.println("DeleteAll");
        Optional<Student> byId = studentRepositoryElasticsearch.findById("61dae9b7f89ce41bb0028893");
        System.out.println(byId);
        studentRepositoryElasticsearch.deleteById("61dae9b7f89ce41bb0028893");
        studentRepositoryElasticsearch.deleteAll();
    }

    public void moveData(){
        List<Student> students = studentRepositoryMongo.findAll();
        System.out.println(students);
        studentRepositoryElasticsearch.saveAll(students);
    }

//    public SearchHit<TotalScoreResponse> sumScore(){
//        SearchHit<TotalScoreResponse> sumScore = studentRepositoryElasticsearch.sumScore();
//        return sumScore;
//    }
//
//    public SearchHit<Student> findOne(){
//        SearchHit<Student> sumScore = studentRepositoryElasticsearch.findOne();
//        return sumScore;
//    }

    public SearchResponse totalScore(){
        try {
            SearchRequest searchRequest = new SearchRequest("person");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.scriptField("TotalScore", new Script("doc['math_score'].value + doc['physic_score'].value + doc['chemistry_score'].value"));
            searchSourceBuilder.fetchField("name");
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
            SearchRequest searchRequest = new SearchRequest("person");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.scriptField("TotalScore", new Script("(doc['math_score'].value + doc['physic_score'].value + doc['chemistry_score'].value)/3"));
            searchSourceBuilder.fetchField("name");

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

//    public SearchResponse ranking(){
//        try {
//            SearchRequest searchRequest = new SearchRequest("person");
//            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//            searchSourceBuilder.scriptField("TotalScore", new Script("doc['math_score'].value + doc['physic_score'].value + doc['chemistry_score'].value"));
//            searchSourceBuilder.fetchField("name");
//            searchSourceBuilder.sort("", SortOrder.DESC);
//            searchRequest.source(searchSourceBuilder);
//            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            for (org.elasticsearch.search.SearchHit hit : searchResponse.getHits()) {
//                Map<String, DocumentField> fields = hit.getFields();
//                log.info("Field" + fields);
//            }
//            return searchResponse;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
