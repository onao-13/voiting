package com.example.voiting.service;

import com.example.voiting.entity.Voice;

public interface VoiceService {
    void sendVoice(Voice voice, long id);
    boolean checkCode(long code, long id);
}
