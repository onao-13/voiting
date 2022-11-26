package com.example.voiting.service.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoiceService;
import com.example.voiting.system.VoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    private VoiceDao voiceDao;

    @Override
    public void sendVoice(Voice voice, long id) {
        switch (voice.getVoice()) {
            case VoiceData.VOICE_FOR: voiceDao.saveVoice(VoiceData.VOTE_FOR, id); break;
            case VoiceData.VOICE_AGAINST: voiceDao.saveVoice(VoiceData.VOTE_AGAINST, id); break;
        }
    }
}
