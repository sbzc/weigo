package com.hlxy.mongodb.service;

import com.hlxy.mongodb.pojo.Comment;

import java.util.List;

public interface MongoDBTest {

    Comment insert(Comment comment);
    Long removeObj();
    Long removeQuery();
    List<Comment> findAll();
    Long updateFirstComment();
    Long updateMultiComment();
    List<Comment> findQueryComment();
}
