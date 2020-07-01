package com.hlxy.mongodb.service;

import com.hlxy.mongodb.pojo.Comment;

import java.util.List;

public interface CommentService02 {
      Long pushUser();
      Long pullUser();
      Long updateComment();
    List<Comment> find();
    List<Comment> advice();

}
