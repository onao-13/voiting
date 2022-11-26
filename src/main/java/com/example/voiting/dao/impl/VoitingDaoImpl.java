package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.*;
import com.example.voiting.service.CodeService;
import com.example.voiting.system.Database;
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
        Database.QUESTION_REF.document(String.valueOf(Database.newDocumentId))
                .set(new Question(voiting.getName(), voiting.getDescription()));
        createEvent(voiting);
    }

    @Override
    public void delete(long id) {
        Database.QUESTION_REF.document(String.valueOf(id)).delete();
        Database.EVENTS_REF.document(String.valueOf(id)).delete();
    }

    @Override
    public void update(long id, Event event) {
        DocumentReference ref = Database.EVENTS_REF.document(String.valueOf(id));
        Map<String, Object> updatedVoiting = new HashMap<>();
        updatedVoiting.put("title", event.getTitle());
        updatedVoiting.put("name", event.getName());
        updatedVoiting.put("description", event.getDescription());
        updatedVoiting.put("date", event.getDate());
        updatedVoiting.put("total", event.getTotal());
        codeService.updateCodes(event.getTotal(), id);
        ref.update(updatedVoiting);
        updateQuestion(id, event.getName(), event.getDescription());
    }

    private void updateQuestion(long id, String name, String description) {
        DocumentReference ref = Database.QUESTION_REF.document(String.valueOf(id));
        Map<String, Object> updatedQuestion = new HashMap<>();
        updatedQuestion.put("name", name);
        updatedQuestion.put("description", description);
        ref.update(updatedQuestion);
    }

    private void createEvent(Voiting voiting) {
        List<Code> codes = codeService.generateCodes(voiting.getTotal());

        Event event = Event.builder()
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
                .build();

        Database.EVENTS_REF.document(String.valueOf(Database.newDocumentId)).create(event);
    }

    private Link getVoitingLink(long id) {

        final String BASE_URL = "https://voiting-server.cfapps.us10-001.hana.ondemand.com";
        final String VOICE_API = "api/voice";

        if (checkId(id)) {
            String url = String.format(
                    "%s/%s/question/%d",
                    BASE_URL,
                    VOICE_API,
                    id
            );
            Link link = new Link();
            link.setUrl(url);
            return link;
        }
        return new Link();
    }

    @Override
    public Optional<Question> getById(long id) {
        try {
            ApiFuture<DocumentSnapshot> snapshot = Database.QUESTION_REF.document(String.valueOf(id)).get();
            return Optional.ofNullable(snapshot.get().toObject(Question.class));
        } catch (Exception e) {
            System.out.println("VoitingDao: getById " + e);
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
