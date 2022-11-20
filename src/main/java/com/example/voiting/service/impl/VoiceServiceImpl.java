package com.example.voiting.service.impl;

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

    /**
     * TODO: UPDATE THIS
     */
    @Override
    public void sendVoice(Voice voice) {
//        switch (voice.getVoice()) {
//            case "yes":
//                behind++;
//                break;
//            case "no":
//                ahead++;
//                break;
//            default:
//                break;
//        }
    }

    public boolean checkCode(int code, long id) {
        if (codeService.isCodeActive(Code.builder().code(code).build(), id)) {
            return true;
        }
        return false;
    }
}
