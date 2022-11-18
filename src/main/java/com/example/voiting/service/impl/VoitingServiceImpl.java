package com.example.voiting.service.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        codeService.regenerateAllCodes();
    }

    @Override
    public Optional<Voiting> getVoitingById(long id) {
        return voitingDao.getById(id);
    }

    @Override
    public Optional<VoitingResult> getVoitingResultById(long id) {
        return voitingDao.getResult(id);
    }
}
