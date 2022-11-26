package com.example.voiting.system;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class Database {

    /**
     * TODO: UPADATE
     */
    @PostConstruct
    private void initialize() {
        try {
            //Delete
            newDocumentId = (Long) INFO_REF.document("doc-id").get().get().get("id");
        } catch (Exception e) {
            newDocumentId = 0;
            System.out.println(e);
        }
    }
    private static final Firestore FIRESTORE = FirestoreClient.getFirestore();
    public static final CollectionReference QUESTION_REF = FIRESTORE.collection("questions");
    public static final CollectionReference EVENTS_REF = FIRESTORE.collection("events");
    private static final CollectionReference INFO_REF =  FIRESTORE.collection("info");
    public static long newDocumentId;

    public static void update() {
        newDocumentId++;
        INFO_REF.document("doc-id").update("id", newDocumentId);
    }
}
