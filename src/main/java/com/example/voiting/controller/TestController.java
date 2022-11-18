package com.example.voiting.controller;

import com.example.voiting.entity.Post;
import com.example.voiting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("get-data")
    public ResponseEntity getTestData() {
        return ResponseEntity.ok(testService.getTestData());
    }

    @PostMapping("add-post")
    public ResponseEntity addTestData(@RequestBody Post post) {
        testService.addTestData(post);
        return ResponseEntity.ok().build();
    }
}
