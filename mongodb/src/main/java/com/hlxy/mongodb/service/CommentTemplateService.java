package com.hlxy.mongodb.service;

import com.hlxy.mongodb.pojo.Comment;

import java.util.List;

public interface CommentTemplateService {

    List<Comment> findAll();
    List<Comment> findByQuery();
    Comment add();
    Comment updateComment();
    long delectComment();
}
