package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.repository.base.StudentRepository;
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
        student.setAge(20);
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
