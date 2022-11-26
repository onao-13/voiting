package com.example.voiting.controller;

import com.example.voiting.entity.Event;
import com.example.voiting.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }
}
