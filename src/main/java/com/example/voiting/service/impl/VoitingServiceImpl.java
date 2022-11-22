package com.example.voiting.service.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.Link;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoitingService;
import com.example.voiting.system.Rest;
import lombok.extern.slf4j.Slf4j;
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
        codeService.createAndGenerateCodes();
    }

    @Override
    public Optional<Voiting> getVoitingById(long id) {
        return voitingDao.getById(id);
    }

    @Override
    public Optional<VoitingResult> getVoitingResultById(long id) {
        return voitingDao.getResult(id);
    }

    @Override
    public Optional<Link> getVoitingLink(long id) {
        if (isVoitingExist(id)) {

            String url = String.format(
                    "%s/%s/question/%d",
                    Rest.BASE_URL,
                    Rest.VOICE_API,
                    id
            );
            Link link = new Link();
            link.setUrl(url);
            return Optional.of(link);
        }
        return Optional.empty();
    }

    private boolean isVoitingExist(long id) {
        if (voitingDao.checkId(id)) return true;
        return false;
    }
}
