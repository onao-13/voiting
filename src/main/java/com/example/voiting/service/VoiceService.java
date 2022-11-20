package com.example.voiting.service;

import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;

public interface VoiceService {
    void sendVoice(Voice voice);
    boolean checkCode(Code code);
}
