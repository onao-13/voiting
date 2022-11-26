package com.example.voiting.controller;

import com.example.voiting.entity.Event;
import com.example.voiting.entity.Voiting;
import com.example.voiting.service.VoitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/voiting")
public class VoitingController {

    @Autowired
    private VoitingService voitingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    ResponseEntity createVoiting(@RequestBody Voiting voiting) {
        voitingService.createVoiting(voiting);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    ResponseEntity deleteVoiting(@PathVariable("id") Long id) {
        voitingService.deleteVoiting(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    ResponseEntity updateVoiting(@PathVariable("id") Long id, @RequestBody Event event) {
        voitingService.updateVoiting(id, event);
        return ResponseEntity.ok().build();
    }
}
