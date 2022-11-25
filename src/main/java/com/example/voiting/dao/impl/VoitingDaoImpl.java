package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.*;
import com.example.voiting.service.CodeService;
import com.example.voiting.system.Database;
import com.example.voiting.system.Rest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VoitingDaoImpl implements VoitingDao {

    @Autowired
    private CodeService codeService;

    @Override
    public void create(Voiting voiting) {
        Database.VOITING_REF.document(String.valueOf(Database.newDocumentId)).set(voiting);
        createEvent(voiting, codeService.createAndGenerateCodes(voiting.getTotal()));
    }

    @Override
    public void delete(long id) {
        Database.VOITING_REF.document(String.valueOf(id)).delete();
        Database.EVENTS_REF.document(String.valueOf(id)).delete();
        Database.CODE_REF.document(String.valueOf(id)).delete();
    }

    @Override
    public void update(long id, Event event) {
        DocumentReference ref = Database.EVENTS_REF.document(String.valueOf(id));
        try {
            System.out.println("VoitingResult: update: \n" +
                    ref.get().get().toObject(Event.class).toString() + "\n"
            );
        } catch (Exception e) {
            System.out.println("VoitingDao: update " + e);
        }
        Map<String, Object> updatedVoiting = new HashMap<>();
        updatedVoiting.put("title", event.getTitle());
        updatedVoiting.put("name", event.getName());
        updatedVoiting.put("description", event.getDescription());
        updatedVoiting.put("date", event.getDate());
        updatedVoiting.put("total", event.getTotal());
        updatedVoiting.put("passwords", codeService.createAndGenerateCodes(event.getTotal()));
        ref.update(updatedVoiting);
    }

    /**
     * TODO: ADD CHANGE TO VOICE COUNT
     */
    private void createEvent(Voiting voiting, List<Code> codes) {
        Database.EVENTS_REF.document(String.valueOf(Database.newDocumentId)).create(
            Event.builder()
                    .id(Database.newDocumentId)
                    .title(voiting.getTitle())
                    .name(voiting.getName())
                    .description(voiting.getDescription())
                    .date(voiting.getDate())
                    .link(getVoitingLink(Database.newDocumentId).getUrl())
                    .voteAgainst(0)
                    .voteFor(0)
                    .total(voiting.getTotal())
                    .passwords(codes)
                    .build()
        );
    }

    private Link getVoitingLink(long id) {
        if (isVoitingExist(id)) {

            String url = String.format(
                    "%s/%s/question/%d",
                    Rest.BASE_URL,
                    Rest.VOICE_API,
                    id
            );
            Link link = new Link();
            link.setUrl(url);
            return link;
        }
        return new Link();
    }

    private boolean isVoitingExist(long id) {
        if (checkId(id)) return true;
        return false;
    }

    @Override
    public Optional<Question> getById(long id) {
        try {
            ApiFuture<DocumentSnapshot> snapshot = Database.VOITING_REF.document(String.valueOf(id)).get();
            /**
             * TODO: UPDATE
             */
            return Optional.ofNullable(snapshot.get().toObject(Question.class));
        } catch (Exception e) {
            System.out.println("VoitingDao: getById " + e);
            return Optional.empty();
        }
    }

    /**
     * TODO: OPTIMIZE THIS
     */
    @Override
    public Optional<Event> getResult(long id) {
        try {
            DocumentReference ref = Database.EVENTS_REF.document(String.valueOf(id));
            DocumentSnapshot snapshot = ref.get().get();
            Event result = null;
            if (snapshot.exists()) {
                result = snapshot.toObject(Event.class);
            }
            return Optional.ofNullable(result);
        } catch (Exception e) {
            System.out.println("VoitingDao: getResult " + e);
            return Optional.empty();
        }
    }

    @Override
    public List<Event> getAllResults() {
        List<Event> result = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> snapshot = Database.EVENTS_REF.get();
            List<QueryDocumentSnapshot> documents = snapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                result.add(document.toObject(Event.class));
            }
        } catch (Exception e) {
            System.out.println("VoitingDao: getAllResult " + e);
        }
        return result;
    }

    @Override
    public boolean checkId(long id) {
        if (id <= Database.newDocumentId && id > 0) return true;
        return false;
    }
}
