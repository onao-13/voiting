package com.example.voiting.entity;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VoitingResult {
    private String title;
    private String name;
    private String description;
    private String date;
    private long voiceCount;
    private long forVoiceCount;
    private long againstVoiceCount;
}
