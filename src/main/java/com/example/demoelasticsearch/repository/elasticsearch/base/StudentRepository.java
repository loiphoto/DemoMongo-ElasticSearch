package com.example.demoelasticsearch.repository.elasticsearch.base;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.response.TotalScoreResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

//    @Query("{ \"fields\": [\"name\"], \"script_fields\" : { \"sumScore\" : { \"script\" : { \"lang\": \"painless\", \"source\": \"doc['math_score'].value + doc['physic_score'].value\" } } } }")
//    SearchHit<TotalScoreResponse> sumScore();
//
//    @Query("{ \"query\": { \"match\" : { \"name\" : \"Loi\" } } }")
//    SearchHit<Student> findOne();
}
