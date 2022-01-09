package com.example.demoelasticsearch.controller;


import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.repository.mongodb.impl.StudentRepositoryImpl;
import com.example.demoelasticsearch.service.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class HomeController {

    private StudentServiceImpl studentService;

    @GetMapping("/elastic")
    public Optional<Student> find() {
        Optional<Student> student = studentService.find();
        return student;
    }

    @GetMapping("/elasticAll")
    public Iterable<Student> findAll() {
        Iterable<Student> all = studentService.findALL();
        return all;
    }

    @DeleteMapping("/elasticAll")
    public void deleteAll() {
        studentService.deleteAll();
    }

    @PostMapping("/elastic")
    public Student insert() {
        Student insert = studentService.insert();
        return insert;
    }

    @PostMapping("/mongo/students")
    public ResponseEntity insertMongodb(@RequestBody StudentRequest student) {
        System.out.println("Insertmongo");
        studentService.insertMongo(student);
        return ResponseEntity.ok().body("Thêm thành công");
    }

    @GetMapping("/mongo/students")
    public List<Student> findAllMongodb() {
        System.out.println("Find all student mongo");
        List<Student> studentList = studentService.findAllMongo();
        return studentList;
    }

    @GetMapping("/move_data")
    public ResponseEntity move() {
        System.out.println("Move data");
        studentService.moveData();
        return ResponseEntity.ok().body("Chuyển thành công");
    }

}
