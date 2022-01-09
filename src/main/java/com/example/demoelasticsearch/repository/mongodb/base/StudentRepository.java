package com.example.demoelasticsearch.repository.mongodb.base;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;

import java.util.List;

public interface StudentRepository {

    void insert(StudentRequest student);

    List<Student> findAll();
}
