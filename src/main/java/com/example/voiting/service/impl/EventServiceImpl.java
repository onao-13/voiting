package com.example.voiting.service.impl;

import com.example.voiting.entity.Event;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.EventService;
import com.example.voiting.service.VoitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private CodeService codeService;

    @Autowired
    private VoitingService voitingService;

    /**
     * TODO: OPTIMIZE
     */
    @Override
    public List<Event> getAllEvents() {
        return voitingService.getAllEvents();
    }
}
