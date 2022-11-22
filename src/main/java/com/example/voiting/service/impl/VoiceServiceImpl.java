package com.example.voiting.service.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.VoiceService;
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
    public void sendVoice(Voice voice) {
        Map<String, Object> voiceResult = new HashMap<>();

        switch (voice.getVoice()) {
            case "yes": voiceResult.put("forVoiceCount", 1); break;
            case "no": voiceResult.put("againstVoiceCount", 1); break;
        }

        saveVoice(voiceResult, voice.getId());
    }

    public boolean checkCode(int code, long id) {
        if (codeService.isCodeActive(Code.builder().code(code).build(), id)) {
            return true;
        }
        return false;
    }

    private void saveVoice(Map<String, Object> voice, long id) {
        voiceDao.saveVoice(voice, id);
    }
}
