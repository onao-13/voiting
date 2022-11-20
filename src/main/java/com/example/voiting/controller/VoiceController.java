package com.example.voiting.controller;

import com.example.voiting.entity.*;
import com.example.voiting.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/voice")
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    /**
     * TODO: UPDATE VOICE-CONTROLLER
     */
    @PostMapping("/send")
    ResponseEntity sendVoice(@RequestBody Voice voice) {
        voiceService.sendVoice(voice);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO: IS FALSE REDIRECT TO ERROR PAGE
     */
    @GetMapping("/voiting")
    ModelAndView getVoiting(@RequestBody GetVoiting voiting) {
        if (voiceService.checkCode(voiting.getCode(), voiting.getId())) {
            return new ModelAndView("redirect:/api/voiting/question/" + voiting.getId());
        }
        return new ModelAndView("redirect:/api/voiting");
    }
}
