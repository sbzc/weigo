package com.hlxy.mongodb;

import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.service.CommentService02;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo02 {
    @Autowired
    private CommentService02 commentService02;
    @Test
    public void pushUser(){
        Long aLong = commentService02.pushUser();
        System.out.println(aLong);
    }
    @Test
    public void pullUser(){
        Long aLong = commentService02.pullUser();
        System.out.println(aLong);
    }
    @Test
    public void updateComment(){
        Long aLong = commentService02.updateComment();
        System.out.println(aLong);
    }
    @Test
    public void find(){
        List<Comment> comments = commentService02.find();
        System.out.println(comments);
    }
    @Test
    public void advice(){
        List<Comment> advice = commentService02.advice();
    }
}
