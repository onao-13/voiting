package com.example.voiting.service;

import com.example.voiting.entity.Code;

import java.util.ArrayList;
import java.util.List;

public interface CodeService {
    boolean isCodeActive(Code code, long id);
    void createAndGenerateCodes();
    void regenerateAllCodes(long id);
    void regenerateOneCode(long id);
    List<Code> getAllActiveCodes(long id);
    void disableAllCodes(long id);
    void disableCode(Code code, long id);
}
