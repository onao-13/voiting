package com.example.voiting.dao;

import com.example.voiting.entity.Code;

import java.util.Map;

public interface VoiceDao {
    void saveVoice(Map<String, Object> voice, long id);
    void disableCode(long id);
}
