package com.example.voiting.service;

import com.example.voiting.entity.Code;

import java.util.ArrayList;

public interface CodeService {
    boolean isCodeActive(Code code);
    void regenerateAllCodes();
    void regenerateOneCode();
    ArrayList<Code> getAllActiveCodes();
    void disableAllCodes();
    void disableCode(Code code);
}
