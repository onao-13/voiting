package com.example.voiting.controller;

import com.example.voiting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("test")
    public ResponseEntity getTestData() {
        return ResponseEntity.ok(testService.getTestData());
    }
}
