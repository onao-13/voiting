package com.example.voiting.entity;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Event {
    private int id;
    private String title;
    private String name;
    private String description;
    private String date;
    private String link;
    private long total;
    private long voteFor;
    private long voteAgainst;
    private List<Code> passwords = new ArrayList<>();
}
