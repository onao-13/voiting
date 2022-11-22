package com.example.voiting.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class VoitingResult {
    private Voiting voiting;
    private long voiceCount;
    private long forVoiceCount;
    private long againstVoiceCount;
}
