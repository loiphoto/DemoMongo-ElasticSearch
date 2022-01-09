package com.example.demoelasticsearch.controller;


import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.repository.mongodb.impl.StudentRepositoryImpl;
import com.example.demoelasticsearch.service.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class HomeController {

    private StudentServiceImpl userService;

    private StudentRepositoryImpl studentRepository;

    @GetMapping("/elastic")
    public Optional<Student> find() {
        Optional<Student> student = userService.find();
        return student;
    }

    @PostMapping("/elastic")
    public Student insert() {
        Student insert = userService.insert();
        return insert;
    }

    @PostMapping("/mongo/students")
    public ResponseEntity insertMongodb(@RequestBody StudentRequest student) {
        System.out.println("Insertmongo");
        studentRepository.insert(student);
        return ResponseEntity.ok().body("Thêm thành công");
    }

    @GetMapping("/mongo/students")
    public List<Student>  findAllMongodb() {
        System.out.println("Find all student mongo");
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

}
