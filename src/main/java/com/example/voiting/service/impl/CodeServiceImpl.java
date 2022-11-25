package com.example.voiting.service.impl;

import com.example.voiting.dao.CodeDao;
import com.example.voiting.entity.Code;
import com.example.voiting.service.CodeService;
import com.example.voiting.system.Database;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    public boolean isCodeActive(Code code, long id) {
        try {
            List<Code> codes = codeDao.getCodes(id);
            AtomicBoolean result = new AtomicBoolean(false);
            codes.forEach(code1 -> {
                if (code1.getCode() == code.getCode()) result.set(true);
            });
            return result.get();
        } catch (Exception e) {
            System.out.println("CodeService: isCodeActive " + e);
            return false;
        }
    }

    @Override
    public List<Code> createAndGenerateCodes(long count) {
        List<Code> codes = generateCodes(count);
        codeDao.saveNewCodes(codes);
        return codes;
    }

    @Override
    public void regenerateAllCodes(long id, long count) {
        codeDao.saveCodes(generateCodes(count), id);
    }

    @Override
    public void regenerateOneCode(long id) {

    }

    @Override
    public List<Code> getAllActiveCodes(long id) {
        return codeDao.getCodes(id);
    }

    @Override
    public void disableAllCodes(long id) {
        codeDao.disableAllCodes(id);
    }

    private Code generate() {
        Code code = new Code();
        code.setCode(
            1000 + (int)(Math.random() * ((9999 - 1000) + 1))
        );
        return code;
    }

    private List<Code> generateCodes(long codeCount) {
        List<Code> newCodes = new ArrayList<Code>();
        for (int i = 0; i < codeCount; i++) {
            newCodes.add(generate());
        }
        return newCodes;
    }
}
