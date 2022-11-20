package com.example.voiting.controller;

import com.example.voiting.entity.Code;
import com.example.voiting.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @PostMapping("/regenerate-codes")
    ResponseEntity generateCodes() {
//        codeService.regenerateAllCodes();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/regenerate-one-code")
    ResponseEntity generateOneCode() {
//        codeService.regenerateOneCode();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active-codes/{id}")
    ResponseEntity getAllActiveCodes(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(codeService.getAllActiveCodes(id));
    }


}
