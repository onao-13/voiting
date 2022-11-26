package com.example.voiting.service;

import com.example.voiting.entity.Code;

import java.util.List;

public interface CodeService {
    boolean isCodeActive(Code code, long id);
    List<Code> generateCodes(long count);
    void updateCodes(long count, long id);
    List<Code> getCodes(long id);
}
