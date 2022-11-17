package com.example.voiting.dao.impl;

import com.example.voiting.dao.TestDao;
import com.example.voiting.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TestDaoImpl implements TestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Post getPost(long id) {
        return entityManager.createQuery(
            "select p from Post p " +
                    "where p.id = :id", Post.class
        )
        .setParameter("id", id)
        .getSingleResult();
    }

    @Override
    @Transactional
    public void addPost(Post post) {
        entityManager.persist(post);
    }
}
