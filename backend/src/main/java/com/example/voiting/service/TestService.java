package com.example.voiting.service;

import com.example.voiting.entity.Post;

import java.util.ArrayList;
import java.util.List;

public interface TestService {
    List<Post> getTestData();
    Post getOnePost();
    void addTestData(Post post);
}
