package com.example.voiting.dao;

import com.example.voiting.entity.Event;
import com.example.voiting.entity.Question;
import com.example.voiting.entity.Voiting;

import java.util.List;
import java.util.Optional;

public interface VoitingDao {
    void create(Voiting voiting);
    void delete(long id);
    void update(long id, Event event);
    Optional<Question> getById(long id);
    Optional<Event> getResult(long id);
    boolean checkId(long id);
    List<Event> getAllResults();
}
