package com.example.voiting.service;

import com.example.voiting.entity.Event;
import com.example.voiting.entity.Link;
import com.example.voiting.entity.Question;
import com.example.voiting.entity.Voiting;

import java.util.List;
import java.util.Optional;

public interface VoitingService {
    void createVoiting(Voiting voiting);
    void deleteVoiting(long id);
    void updateVoiting(long id, Event event);
    Optional<Question> getVoitingById(long id);
    Optional<Event> getEventById(long id);
    List<Event> getAllEvents();
}
