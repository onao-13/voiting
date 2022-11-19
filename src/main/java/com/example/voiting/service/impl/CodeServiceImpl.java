package com.example.voiting.service.impl;

import com.example.voiting.entity.Code;
import com.example.voiting.service.CodeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CodeServiceImpl implements CodeService {
    private ArrayList<Code> activeCodes = new ArrayList<Code>();

    public boolean isCodeActive(Code code) {
        try {
            activeCodes.get(activeCodes.indexOf(code));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void regenerateAllCodes() {
        for (int i = 0; i < 30; i++) {
            activeCodes.add(generate());
        }
    }

    @Override
    public void regenerateOneCode() {
        activeCodes.add(generate());
    }

    @Override
    public ArrayList<Code> getAllActiveCodes() {
        return activeCodes;
    }

    @Override
    public void disableAllCodes() {
        activeCodes = new ArrayList<>();
    }

    @Override
    public void disableCode(Code code) {
        activeCodes.remove(code);
    }

    private Code generate() {
        Code code = new Code();
        code.setCode(
            1000 + (int)(Math.random() * ((9999 - 1000) + 1))
        );
        return code;
    }
}
