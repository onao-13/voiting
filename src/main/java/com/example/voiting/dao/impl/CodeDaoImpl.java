package com.example.voiting.dao.impl;

import com.example.voiting.dao.CodeDao;
import com.example.voiting.entity.Code;
import com.example.voiting.system.Database;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodeDaoImpl implements CodeDao {

    @Override
    public void saveNewCodes(List<Code> codes) {
        Map<String, List<Code>> docCodes = new HashMap<String, List<Code>>();
        docCodes.put("codes", codes);
        Database.CODE_REF.document(String.valueOf(Database.newDocumentId)).set(docCodes);
    }

    @Override
    public void saveCodes(List<Code> codes, long id) {
        Map<String, List<Code>> docCodes = new HashMap<>();
        docCodes.put("codes", codes);
        Database.CODE_REF.document(String.valueOf(id)).set(docCodes);
    }

    @Override
    public void saveCode(Code code, long id) {
        Map<String, List<Code>> docCodes = new HashMap<>();
    }

    /**
     * TODO: ADD CASHING (OPTIONAL)
     */
    @Override
    public List<Code> getCodes(long id) {
        try {
            DocumentSnapshot snapshot = Database.CODE_REF.document(String.valueOf(id)).get().get();
            ArrayList<Map<String, Object>> result = (ArrayList<Map<String,Object>>) snapshot.get("codes");
            ArrayList<Code> codes = new ArrayList<>();
            result.forEach(code -> {
                    codes.add(Code.builder()
                        .code((Long) code.get("code"))
                        .build());   
                }
            );
            return codes;
        } catch (Exception e) {
            System.out.println("CodeDao: getCodes " + e);
            return new ArrayList<>();
        }
    }

    @Override
    public void disableAllCodes(long id) {
        try {
            DocumentReference snapshot = Database.CODE_REF.document(String.valueOf(id));
            Map<String, Object> codes = new HashMap<>();
            codes.put("codes", FieldValue.delete());
            snapshot.update(codes);
        } catch (Exception e) {
            System.out.println("CodeDao: disableAllCodes " + e);
        }
    }
}
