package com.example.voiting.controller;

import com.example.voiting.entity.*;
import com.example.voiting.service.VoiceService;
import com.example.voiting.service.VoitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * TODO: CREATE LOG IN
 */
@RestController
@RequestMapping("/api/voice")
//@EnableMethodSecurity(securedEnabled = true)
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private VoitingService voitingService;

    /**
     * TODO: UPDATE VOICE-CONTROLLER
     */
//    @PreAuthorize("hasRole('VOTING')")
    @PostMapping("/question/{id}/send")
    ResponseEntity sendVoice(@PathVariable("id") Long id, @RequestBody Voice voice) {
        voiceService.sendVoice(voice, id);
        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasRole('VOTING')")
    @GetMapping("/question/{id}")
    ResponseEntity<Question> getQuestionById(@PathVariable(name = "id") long id) {
        Optional<Question> result = voitingService.getVoitingById(id);
        return result.map(voiting -> ResponseEntity.ok().body(voiting))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    /**
     * TODO: UPDATE THIS
     */
    @PostMapping("/verification/{id}")
    ModelAndView getVoiting(@PathVariable("id") Long id, @RequestBody Code code) {
        if (voiceService.checkCode(code.getCode(), id)) {
            return new ModelAndView("redirect:/api/voice/question/" + id);
        }
        return new ModelAndView("redirect:/api/voiting");
    }
}
