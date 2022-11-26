package com.example.voiting.service.impl;

import com.example.voiting.dao.CodeDao;
import com.example.voiting.entity.Code;
import com.example.voiting.service.CodeService;
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
            Boolean result =false;
            List<Code> codes = codeDao.getCodes(id);
            for (Iterator<Code> codesIterator = codes.listIterator(); codesIterator.hasNext();) {
                if (codesIterator.next().getCode() == code.getCode()) {
                    result = true;
                    codesIterator.remove();
                }
            }
            codeDao.update(codes, id);
            return result;
        } catch (Exception e) {
            System.out.println("CodeService: isCodeActive " + e);
            return false;
        }
    }

    @Override
    public List<Code> generateCodes(long count) {
        List<Code> codes = generateCodesByCount(count);
        return codes;
    }

    @Override
    public void updateCodes(long count, long id) {
        List<Code> codes = generateCodes(count);
        codeDao.update(codes, id);
    }

    @Override
    public List<Code> getCodes(long id) {
        return codeDao.getCodes(id);
    }

    private Code generate() {
        Code code = new Code();
        code.setCode(
            1000 + (int)(Math.random() * ((9999 - 1000) + 1))
        );
        return code;
    }

    private List<Code> generateCodesByCount(long codeCount) {
        List<Code> newCodes = new ArrayList<Code>();
        for (int i = 0; i < codeCount; i++) {
            newCodes.add(generate());
        }
        return newCodes;
    }
}
