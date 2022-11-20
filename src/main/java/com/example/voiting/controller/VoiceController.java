package com.example.voiting.controller;

import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
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

    @PostMapping("/send")
    ResponseEntity sendVoice(@RequestBody Voice voice) {
        voiceService.sendVoice(voice);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO: IS FALSE REDIRECT TO ERROR PAGE
     */
    @GetMapping("/voiting")
    ModelAndView getVoiting(@RequestBody Code code, Long id) {
        if (voiceService.checkCode(code)) {
            return new ModelAndView("redirect:/api/voiting/question/" + id);
        }
        return new ModelAndView("redirect:/api/voiting");
    }
}
