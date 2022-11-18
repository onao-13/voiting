package com.example.voiting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Post {
    private long id;

    @NonNull
    @JsonIgnore
    private long userId;

    @NonNull
    private String title;

    @NonNull
    private String body;
}
