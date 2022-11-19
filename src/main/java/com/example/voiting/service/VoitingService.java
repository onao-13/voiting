package com.example.voiting.service;

import com.example.voiting.entity.Link;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;

import java.util.Optional;

public interface VoitingService {
    void createVoiting(Voiting voiting);
    Optional<Voiting> getVoitingById(long id);
    Optional<VoitingResult> getVoitingResultById(long id);
    Optional<Link> getVoitingLink(long id);
}
