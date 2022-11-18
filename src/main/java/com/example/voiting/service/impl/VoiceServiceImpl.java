package com.example.voiting.service.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.service.CodeService;
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

    /**
     * TODO: ADD THIS
     */
//    @Autowired
//    private VoiceDao voiceDao;

    @Autowired
    private CodeService codeService;

    @Override
    public void sendVoice(Voice voice) {
        if (voice.getVoice() =="yes") {
            behind++;
        } else if (voice.getVoice() == "no") {
            ahead++;
        }
    }

    /**
     * TODO: UPDATE THIS, OPTIONAL: Optional<Voiting>
     */
    @Override
    public Voiting getVoiting(Code code) {
        if (checkCode(code)) {
            System.out.println(checkCode(code));
            codeService.disableCode(code);
            System.out.println(checkCode(code));
        }
        return null;
    }

    private boolean checkCode(Code code) {
        return codeService.checkCode(code);
    }

    /**
     * TODO: UPDATE THIS
     */
    @Override
    public VoitingResult getVoitingResult(long id) {
        return null;
    }
}
