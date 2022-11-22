package com.example.voiting.system;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

public final class Database {
    private static final Firestore FIRESTORE = FirestoreClient.getFirestore();
    public static final CollectionReference VOITING_REF = FIRESTORE.collection("voiting");
    public static final CollectionReference VOITING_RESULT_REF = FIRESTORE.collection("voiting-result");
    public static final CollectionReference CODE_REF = FIRESTORE.collection("codes");
    public static int newDocumentId = getSize() + 1;

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
}
