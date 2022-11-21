package com.example.voiting.controller;

import com.example.voiting.entity.Link;
import com.example.voiting.entity.Voiting;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/voiting")
public class VoitingController {

    @Autowired
    private VoitingService voitingService;

    @PostMapping("/create")
    ResponseEntity createVoiting(@RequestBody Voiting voiting) {
        voitingService.createVoiting(voiting);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-link/{id}")
    ResponseEntity<Link> getLinkById(@PathVariable(name = "id") long id) {
        Optional<Link> link = voitingService.getVoitingLink(id);
        return link.map(url -> ResponseEntity.ok().body(url))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
