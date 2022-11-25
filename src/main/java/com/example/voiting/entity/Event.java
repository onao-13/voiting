package com.example.voiting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private long id;
    private String title;
    private String name;
    private String description;
    private String date;
    private String link;
    private long total;
    private long voteFor;
    private long voteAgainst;
    private List<Code> passwords;
}
