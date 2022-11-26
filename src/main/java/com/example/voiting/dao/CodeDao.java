package com.example.voiting.dao;

import com.example.voiting.entity.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CodeDao {
    void saveNewCodes(List<Code> codes);
    List<Code> getCodes(long id);
    void update(List<Code> codes, long id);
}
