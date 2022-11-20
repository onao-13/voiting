package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.system.Database;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VoitingDaoImpl implements VoitingDao {

    @Override
    public void create(Voiting voiting) {
        Database.VOITING_REF.document(String.valueOf(Database.newDocumentId)).set(voiting);
        createResultFile(voiting);
    }

    private void createResultFile(Voiting voiting) {
        Database.VOITING_RESULT_REF.document(String.valueOf(Database.newDocumentId)).create(
            VoitingResult.builder()
                .voiting(voiting)
                .voitingPercent(0)
                .voiceCount(30)
                .build()
        );
    }

    @Override
    public Optional<Voiting> getById(long id) {
        try {
            ApiFuture<DocumentSnapshot> snapshot = Database.VOITING_REF.document(String.valueOf(id)).get();
            return Optional.ofNullable(snapshot.get().toObject(Voiting.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<VoitingResult> getResult(long id) {
        try {
            ApiFuture<DocumentSnapshot> snapshot = Database.VOITING_RESULT_REF.document(String.valueOf(id)).get();
            return Optional.ofNullable(snapshot.get().toObject(VoitingResult.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean checkId(long id) {
        if (id <= Database.newDocumentId && id > 0) return true;
        return false;
    }
}
