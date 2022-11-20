package com.example.voiting.service.impl;

import com.example.voiting.dao.CodeDao;
import com.example.voiting.entity.Code;
import com.example.voiting.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CodeServiceImpl implements CodeService {
    private List<Code> activeCodes = new ArrayList<Code>();

    @Autowired
    private CodeDao codeDao;

    public boolean isCodeActive(Code code, long id) {

//        boolean active = false;
//
//        for (Object code1 : codes) {
//            System.out.println(code1);
//            if (code1 == code) {
//                System.out.println(code);
//                active = true;
//            }
//        }
//        System.out.println(active);
//        if (active) return true;

        try {
            List<Code> codes = codeDao.getCodes(id);
            AtomicBoolean result = new AtomicBoolean(false);
            codes.forEach(code1 -> {
                if (code1.getCode() == code.getCode()) result.set(true);
            });
            return result.get();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void createAndGenerateCodes() {
        codeDao.saveNewCodes(generateCodes(30));
    }

    @Override
    public void regenerateAllCodes(long id) {

    }

    @Override
    public void regenerateOneCode(long id) {
        activeCodes.add(generate());
    }

    @Override
    public List getAllActiveCodes(long id) {
        return codeDao.getCodes(id);
    }

    @Override
    public void disableAllCodes(long id) {
        activeCodes = new ArrayList<>();
    }

    @Override
    public void disableCode(Code code, long id) {
        activeCodes.remove(code);
    }

    private Code generate() {
        Code code = new Code();
        code.setCode(
            1000 + (int)(Math.random() * ((9999 - 1000) + 1))
        );
        return code;
    }

    private List<Code> generateCodes(int codeCount) {
        List<Code> newCodes = new ArrayList<Code>();
        for (int i = 0; i < codeCount; i++) {
            newCodes.add(generate());
        }
        return newCodes;
    }
}
