package com.example.voiting.service.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceServiceImpl implements VoiceService {
    /**
     * TODO: DELETE THIS
     */
    private int behind = 0;
    private int ahead = 0;

    @Autowired
    private VoiceDao voiceDao;

    @Override
    public void sendVoice(Voice voice) {
        if (voice.getVoice() =="yes") {
            behind++;
        } else if (voice.getVoice() == "no") {
            ahead++;
        }
    }

    /**
     * TODO: UPDATE THIS
     */
    @Override
    public Voiting getVoiting(Code code) {
        if (checkCode(code)) {

        }
        return null;
    }

    /**
     * TODO: UPDATE THIS
     */
    private boolean checkCode(Code code) {
        return true;
    }

    @Override
    public VoitingResult getVoitingResult(long id) {
        return null;
    }
}
