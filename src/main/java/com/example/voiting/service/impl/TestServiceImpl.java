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

//    @Autowired
//    private TestDao testDao;

    @Override
    public List<Post> getTestData() {
//        List<Post> posts = new ArrayList<Post>();
//        for (long i = 1; i < 5; i++) {
//            posts.add(testDao.getPost(i));
//        }

        return arrayListData();
    }

    @Override
    public void addTestData(Post post) {
        System.out.println(post.toString());
//        testDao.addPost(post);
    }

    private List<Post> arrayListData() {
        ArrayList<Post> testData = new ArrayList<Post>();
        testData.add(new Post().builder()
                        .id(1L)
                        .title("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                        .body("quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto")
                .build());

        testData.add(new Post().builder()
                        .id(2L)
                        .title("qui est esse")
                        .body("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla")
                .build());

        testData.add(new Post().builder()
                        .id(3L)
                        .title("ea molestias quasi exercitationem repellat qui ipsa sit aut")
                        .body("et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut")
                .build());

        testData.add(new Post().builder()
                        .id(4L)
                        .title("eum et est occaecati")
                        .body("ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit")
                .build());
        return testData;
    }
}
