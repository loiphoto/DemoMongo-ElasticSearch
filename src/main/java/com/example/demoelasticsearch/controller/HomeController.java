package com.example.demoelasticsearch.controller;


import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.service.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class HomeController {

    private StudentServiceImpl userService;

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

}
