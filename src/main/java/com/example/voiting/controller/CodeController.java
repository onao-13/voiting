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

    @PostMapping("/generate-codes")
    ResponseEntity generateCodes() {
//        codeService.regenerateAllCodes();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generate-one-code")
    ResponseEntity generateOneCode() {
//        codeService.regenerateOneCode();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active-codes/{id}")
    ResponseEntity getAllActiveCodes(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(codeService.getAllActiveCodes(id));
    }

    @PostMapping("/regenerate-codes/{id}")
    ResponseEntity regenerateAllCodes(@PathVariable("id") Long id) {
        codeService.regenerateAllCodes(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/disable-codes/{id}")
    ResponseEntity disableAllCodes(@PathVariable("id") Long id) {
        codeService.disableAllCodes(id);
        return ResponseEntity.ok().build();
    }
}
