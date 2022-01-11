package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.constant.StudentConstant;
import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.repository.elasticsearch.base.StudentRepository;
import com.example.demoelasticsearch.repository.elasticsearch.impl.StudentRepositoryDao;
import com.example.demoelasticsearch.repository.mongodb.impl.StudentRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepositoryElasticsearch;

    @Autowired
    private StudentRepositoryImpl studentRepositoryMongo;

    @Autowired
    private StudentRepositoryDao studentRepositoryDao;


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
        studentRepositoryElasticsearch.deleteAll();
    }

    public void moveData(){
        List<Student> students = studentRepositoryMongo.findAll();
        System.out.println(students);
        studentRepositoryElasticsearch.saveAll(students);
    }

    public SearchResponse totalScore(){
        return studentRepositoryDao.totalScore();
    }

    public SearchResponse averageScore(){
        return studentRepositoryDao.averageScore();
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
