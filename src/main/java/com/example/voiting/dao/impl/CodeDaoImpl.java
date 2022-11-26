package com.example.voiting.dao.impl;

import com.example.voiting.dao.CodeDao;
import com.example.voiting.entity.Code;
import com.example.voiting.system.Database;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodeDaoImpl implements CodeDao {

    @Override
    public void saveNewCodes(List<Code> codes) {
        Map<String, List<Code>> docCodes = new HashMap<String, List<Code>>();
        docCodes.put("passwords", codes);
        Database.EVENTS_REF.document(String.valueOf(Database.newDocumentId)).set(docCodes);
    }

    @Override
    public List<Code> getCodes(long id) {
        ArrayList<Code> codes = new ArrayList<>();
        getCodeDocument(id).forEach(code ->
                codes.add(new Code((long) code.get("code")))
        );
        return codes;
    }

    @Override
    public void update(List<Code> codes, long id) {
        DocumentReference ref = Database.EVENTS_REF.document(String.valueOf(id));
        ref.update("passwords", codes);
    }

    private ArrayList<Map<String, Object>> getCodeDocument(long id) {
        try {
            DocumentSnapshot snapshot = Database.EVENTS_REF.document(String.valueOf(id)).get().get();
            return (ArrayList<Map<String, Object>>) snapshot.get("passwords");
        } catch (Exception e) {
            System.out.println("CodeDao: getCodesDocument " + e);
            return new ArrayList<>();
        }
    }
}
