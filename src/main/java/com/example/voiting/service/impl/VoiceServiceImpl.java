package com.example.voiting.service.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoiceService;
import com.example.voiting.system.VoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    private CodeService codeService;

    @Autowired
    private VoiceDao voiceDao;

    @Override
    public void sendVoice(Voice voice, long id) {
        Map<String, Object> voiceResult = new HashMap<>();

        switch (voice.getVoice()) {
            case VoiceData.VOICE_FOR: voiceResult.put(VoiceData.VOTE_FOR, 1); break;
            case VoiceData.VOICE_AGAINST: voiceResult.put(VoiceData.VOTE_AGAINST, 1); break;
        }

        voiceDao.saveVoice(voiceResult, id);
    }

    public boolean checkCode(long code, long id) {
        if (codeService.isCodeActive(Code.builder().code(code).build(), id)) {
            voiceDao.disableCode(id);
            return true;
        }
        return false;
    }
}
