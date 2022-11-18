package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoitingDao;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class VoitingDaoImpl implements VoitingDao {

    private Firestore db = FirestoreClient.getFirestore();
    private CollectionReference voitingRef = db.collection("voiting");
    private CollectionReference voitingResultRef = db.collection("voiting-result");

    @PostConstruct
    private void init() throws ExecutionException, InterruptedException {
        getDocumentCount();
    }

    long count = 0;

    @Override
    public void create(Voiting voiting) {
        voitingRef.document(String.valueOf(count)).set(voiting);
    }

    @Override
    public Optional<Voiting> getById(long id) {
        try {
            ApiFuture<DocumentSnapshot> snapshot = voitingRef.document(String.valueOf(id)).get();
            return Optional.ofNullable(snapshot.get().toObject(Voiting.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<VoitingResult> getResult(long id) {
        try {
            ApiFuture<DocumentSnapshot> snapshot = voitingResultRef.document(String.valueOf(id)).get();
            return Optional.ofNullable(snapshot.get().toObject(VoitingResult.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void getDocumentCount() {
        try {
            ApiFuture<QuerySnapshot> snapshot = voitingRef.get();
            snapshot.get().getDocuments().forEach(doc -> count++);
        } catch (Exception e) {
            /**
             * TODO: ADD LOGS EXCEPTION
             */
        }
    }
}
