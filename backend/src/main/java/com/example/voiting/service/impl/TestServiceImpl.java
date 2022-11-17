package com.example.voiting.service.impl;

import com.example.voiting.dao.TestDao;
import com.example.voiting.entity.Post;
import com.example.voiting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Post> getTestData() {
        List<Post> posts = new ArrayList<Post>();
        for (long i = 1; i < 5; i++) {
            posts.add(testDao.getPost(i));
        }

        return posts;
    }

    @Override
    public Post getOnePost() {
        return testDao.getPost(1);
    }

    @Override
    public void addTestData(Post post) {
        System.out.println(post.toString());
        testDao.addPost(post);
    }
}
