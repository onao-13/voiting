package com.example.voiting.service;

import com.example.voiting.entity.Code;

import java.util.ArrayList;
import java.util.List;

public interface CodeService {
    boolean isCodeActive(Code code, long id);
    List<Code> createAndGenerateCodes(long count);
    void regenerateAllCodes(long id, long count);
    void regenerateOneCode(long id);
    List<Code> getAllActiveCodes(long id);
    void disableAllCodes(long id);
}
