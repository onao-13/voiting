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
            newDocumentId = snapshot.get().getDocuments().size();
        } catch (Exception e) {
//            log.info("No documents in database");
            newDocumentId = 0;
            System.out.println("No document in database");
        }
    }
    private static final Firestore FIRESTORE = FirestoreClient.getFirestore();
    public static final CollectionReference VOITING_REF = FIRESTORE.collection("voiting");
    public static final CollectionReference VOITING_RESULT_REF = FIRESTORE.collection("voiting-result");
    public static final CollectionReference CODE_REF = FIRESTORE.collection("codes");
    private static long newDocumentId;

    public static int getSize() {
        int size;

        try {
            ApiFuture<QuerySnapshot> snapshot = VOITING_REF.get();
            size = snapshot.get().getDocuments().size();
        } catch (Exception e) {
            size = 1;
            System.out.println(e);
        }

        return size;
    }

    /**
     * TODO: FIX
     */
    public static long setNewDocumentId() {
        newDocumentId++;
        return newDocumentId;
    }

    public static long getNewDocumentId() {
        return newDocumentId;
    }
}
