package com.example.voiting.dao;

import java.util.Map;

public interface VoiceDao {
    void saveVoice(Map<String, Object> voice, long id);
}
