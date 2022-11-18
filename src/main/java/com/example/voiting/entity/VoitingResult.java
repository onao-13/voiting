package com.example.voiting.entity;

import lombok.Data;

@Data
public class VoitingResult extends Voiting {
    private Voiting voiting;
    private int voitingPercent;
}
