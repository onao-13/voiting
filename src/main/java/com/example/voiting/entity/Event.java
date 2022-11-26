package com.example.voiting.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
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
