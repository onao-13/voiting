package com.example.voiting.dao;

import com.example.voiting.entity.Post;

import java.util.ArrayList;

public interface TestDao {
    Post getPost(long id);
    void addPost(Post post);
}
