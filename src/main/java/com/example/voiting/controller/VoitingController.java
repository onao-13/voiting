package com.example.voiting.controller;

import com.example.voiting.entity.Event;
import com.example.voiting.entity.Voiting;
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
        System.out.println("VoitingController: " + voiting.toString());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity deleteVoiting(@PathVariable("id") Long id) {
        voitingService.deleteVoiting(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    ResponseEntity updateVoiting(@PathVariable("id") Long id, @RequestBody Event event) {
        voitingService.updateVoiting(id, event);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/get-link/{id}")
//    ResponseEntity<Link> getLinkById(@PathVariable(name = "id") long id) {
//        Optional<Link> link = voitingService.getVoitingLink(id);
//        return link.map(url -> ResponseEntity.ok().body(url))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
