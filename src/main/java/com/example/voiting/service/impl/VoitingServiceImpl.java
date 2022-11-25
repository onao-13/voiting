package com.example.voiting.service.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.Event;
import com.example.voiting.entity.Link;
import com.example.voiting.entity.Question;
import com.example.voiting.entity.Voiting;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoitingService;
import com.example.voiting.system.Database;
import com.example.voiting.system.Rest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitingServiceImpl implements VoitingService {

    @Autowired
    private VoitingDao voitingDao;

    @Autowired
    private CodeService codeService;

    @Override
    public void createVoiting(Voiting voiting) {
        voitingDao.create(voiting);
        codeService.createAndGenerateCodes(voiting.getTotal());
        Database.update();
    }

    @Override
    public void deleteVoiting(long id) {
        voitingDao.delete(id);
    }

    @Override
    public void updateVoiting(long id, Event event) {
        voitingDao.update(id, event);
    }

    @Override
    public Optional<Question> getVoitingById(long id) {
        return voitingDao.getById(id);
    }

    @Override
    public Optional<Event> getEventById(long id) {
        return voitingDao.getResult(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return voitingDao.getAllResults();
    }
}
