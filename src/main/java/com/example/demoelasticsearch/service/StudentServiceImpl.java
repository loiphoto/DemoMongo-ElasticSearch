package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.repository.elasticsearch.base.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepository;

    public Student insert() {
        Student student = new Student();
        student.setStudentId("1");
        student.setName("Loi");
        student.setClassName("A");
        student.setMathScore(7.5F);
        student.setPhysicScore(8F);
        student.setChemistryScore(8.5F);
        Student save = studentRepository.save(student);
        return save;
    }

    public Optional<Student> find() {
        Iterable<Student> all = studentRepository.findAll();
        System.out.println(all);
        Optional<Student> byId = studentRepository.findById("1");
        return byId;
    }
}
