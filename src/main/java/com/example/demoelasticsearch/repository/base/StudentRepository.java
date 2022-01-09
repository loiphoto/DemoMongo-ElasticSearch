package com.example.demoelasticsearch.repository.base;

import com.example.demoelasticsearch.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
