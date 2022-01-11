package com.example.demoelasticsearch.controller;


import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.model.response.TotalScoreResponse;
import com.example.demoelasticsearch.service.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class HomeController {

    private StudentServiceImpl studentService;

    @GetMapping("/elastic/students")
    public Iterable<Student> findAll() {
        Iterable<Student> all = studentService.findALL();
        return all;
    }

    @DeleteMapping("/elastic/students")
    public void deleteAll() {
        studentService.deleteAll();
    }

    @PostMapping("/mongo/students")
    public ResponseEntity insertMongodb(@RequestBody StudentRequest student) {
        studentService.insertMongo(student);
        return ResponseEntity.ok().body("Thêm thành công");
    }

    @GetMapping("/mongo/students")
    public List<Student> findAllMongodb() {
        List<Student> studentList = studentService.findAllMongo();
        return studentList;
    }

    @GetMapping("/move_data")
    public ResponseEntity move() {
        studentService.moveData();
        return ResponseEntity.ok().body("Chuyển thành công");
    }

//    @GetMapping("/sum")
//    public SearchHit<TotalScoreResponse> sum() {
//        System.out.println("Sum Score");
//        SearchHit<TotalScoreResponse> totalScoreResponses = studentService.sumScore();
//        return totalScoreResponses;
//    }

//    @GetMapping("/findOne")
//    public SearchHit<Student> findOne() {
//        System.out.println("Fine one");
//        SearchHit<Student> findOne = studentService.findOne();
//        return findOne;
//    }

    @PostMapping("/totalScores")
    public SearchResponse totalScore() {
        SearchResponse searchResponse = studentService.totalScore();
        return searchResponse;
    }

    @PostMapping("/averageScores")
    public SearchResponse averageScore() {
        SearchResponse searchResponse = studentService.averageScore();
        return searchResponse;
    }

//    @PostMapping("/ranks")
//    public SearchResponse ranks() {
//        SearchResponse searchResponse = studentService.ranking();
//        return searchResponse;
//    }

}
