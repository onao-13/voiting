package com.example.voiting.system;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class Database {

    @PostConstruct
    private void initialize() {
        try {
            ApiFuture<QuerySnapshot> snapshot = Database.VOITING_REF.get();
            long dbSize = snapshot.get().getDocuments().size();
            size = dbSize;
            newDocumentId = dbSize + 1;
        } catch (Exception e) {
//            log.info("No documents in database");
            newDocumentId = 1;
            System.out.println("No document in database");
        }
    }

    private static final Firestore FIRESTORE = FirestoreClient.getFirestore();
    public static final CollectionReference VOITING_REF = FIRESTORE.collection("voiting");
    public static final CollectionReference VOITING_RESULT_REF = FIRESTORE.collection("voiting-result");
    public static final CollectionReference CODE_REF = FIRESTORE.collection("codes");
    public static long newDocumentId;
    public static long size;
}
