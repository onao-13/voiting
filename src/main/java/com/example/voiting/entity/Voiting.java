package com.example.voiting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Builder
public class Voiting {
    private String name;
    private String date;
    private String rank;
}
