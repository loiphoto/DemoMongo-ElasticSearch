package com.example.demoelasticsearch.repository.elasticsearch.base;

import com.example.demoelasticsearch.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

//    @Query("{ \"fields\": [\"name\"], \"script_fields\" : { \"sumScore\" : { \"script\" : { \"lang\": \"painless\", \"source\": \"doc['math_score'].value + doc['physic_score'].value\" } } } }")
//    SearchHit<TotalScoreResponse> sumScore();
//
//    @Query("{ \"query\": { \"match\" : { \"name\" : \"Loi\" } } }")
//    SearchHit<Student> findOne();
}
