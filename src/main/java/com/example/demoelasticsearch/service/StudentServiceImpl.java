package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.repository.elasticsearch.base.StudentRepository;
import com.example.demoelasticsearch.repository.mongodb.impl.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepositoryElasticsearch;

    @Autowired
    private StudentRepositoryImpl studentRepositoryMongo;

    public Student insert() {
        Student student = new Student();
        student.setStudentId("1");
        student.setName("Loi");
        student.setClassName("A");
        student.setMathScore(7.5F);
        student.setPhysicScore(8F);
        student.setChemistryScore(8.5F);
        Student save = studentRepositoryElasticsearch.save(student);
        return save;
    }

    public Optional<Student> find() {
        Optional<Student> byId = studentRepositoryElasticsearch.findById("1");
        return byId;
    }

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

}
