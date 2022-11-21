package com.example.voiting.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoitingResult {
    private Voiting voiting;
    private int voiceCount;
    private int forVoiceCount;
    private int againstVoiceCount;
}
