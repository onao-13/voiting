package com.example.voiting.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Voiting {
    private String name;
    private String date;
    private String rank;
}
