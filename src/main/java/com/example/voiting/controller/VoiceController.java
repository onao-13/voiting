package com.example.voiting.controller;

import com.example.voiting.entity.Code;
import com.example.voiting.entity.Voice;
import com.example.voiting.entity.Voiting;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voice")
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @PostMapping("/send")
    ResponseEntity sendVoice(@RequestBody Voice voice) {
        voiceService.sendVoice(voice);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/voiting")
    ResponseEntity<Voiting> getVoiting(@RequestBody Code code) {
        Voiting voiting = voiceService.getVoiting(code);
        return ResponseEntity.ok().body(voiting);
    }

    @GetMapping("/result/{id}")
    ResponseEntity<VoitingResult> getVoitingResult(@PathVariable long id) {
        VoitingResult result = voiceService.getVoitingResult(id);
        return ResponseEntity.ok().body(result);
    }
}
