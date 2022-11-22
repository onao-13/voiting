package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.system.Database;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VoitingDaoImpl implements VoitingDao {

    @Override
    public void create(Voiting voiting) {
        Database.VOITING_REF.document(String.valueOf(Database.setNewDocumentId())).set(voiting);
        createResultFile(voiting);
    }

    @Override
    public void delete(long id) {
        Database.VOITING_REF.document(String.valueOf(id)).delete();
        Database.VOITING_RESULT_REF.document(String.valueOf(id)).delete();
        Database.CODE_REF.document(String.valueOf(id)).delete();
    }

    /**
     * TODO: FIX THIS
     */
    @Override
    public void update(long id, Voiting voiting) {
        DocumentReference ref = Database.VOITING_REF.document(String.valueOf(id));
        Map<String, Object> updatedVoiting = new HashMap<>();
        updatedVoiting.put("title", voiting.getTitle());
        updatedVoiting.put("name", voiting.getName());
        updatedVoiting.put("description", voiting.getDescription());
        updatedVoiting.put("date", voiting.getDate());
        ApiFuture<WriteResult> updates = ref.update(updatedVoiting);
    }

    private void createResultFile(Voiting voiting) {
        Database.VOITING_RESULT_REF.document(String.valueOf(Database.getNewDocumentId())).create(
            VoitingResult.builder()
                    .title(voiting.getTitle())
                    .name(voiting.getName())
                    .description(voiting.getDescription())
                    .date(voiting.getDate())
                    .againstVoiceCount(0)
                    .forVoiceCount(0)
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

    /**
     * TODO: OPTIMIZE THIS
     */
    @Override
    public Optional<VoitingResult> getResult(long id) {
        try {
            DocumentReference ref = Database.VOITING_RESULT_REF.document(String.valueOf(id));
            DocumentSnapshot snapshot = ref.get().get();
            VoitingResult result = null;
            if (snapshot.exists()) {
                result = snapshot.toObject(VoitingResult.class);
            }
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean checkId(long id) {
        if (id <= Database.getNewDocumentId() && id > 0) return true;
        return false;
    }
}
