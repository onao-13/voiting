package com.example.voiting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Builder
@ToString
public class Voiting {
    private String title;
    private String name;
    private String description;
    private String date;
    private long total;
}
