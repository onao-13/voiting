package com.example.voiting.dao;

import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;

import java.util.Optional;

public interface VoitingDao {
    void create(Voiting voiting);
    Optional<Voiting> getById(long id);
    Optional<VoitingResult> getResult(long id);
    boolean checkId(long id);
}
